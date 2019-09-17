package com.app.config;

import com.app.service.impl.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import sun.net.httpserver.AuthFilter;

/**
 * @Package com.app.config
 * @ClassName SecurityConfig
 * @Author shaobin.wang
 * @Date 2019/09/16 18:11
 * @Version 1.0
 * @Description:
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private MyUserDetailService myUserDetailService;
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl  tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setDataSource(dataSource);
//        //tokenRepository.setCreateTableOnStartup(true);
//        return tokenRepository;
//    }
    @Override
    //HTTP请求安全处理
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        /*httpSecurity.antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers("/api/ok/**").permitAll()
                .anyRequest().authenticated();*/

        httpSecurity.formLogin().loginPage("/signIn").loginProcessingUrl("/user/userLogin").defaultSuccessUrl("/index")
                .failureUrl("/signIn?msg=error")
                .and()
                .logout().logoutUrl("/logout")
                .and()
                .authorizeRequests()
                .antMatchers("/signIn", "/hello").permitAll()
                .antMatchers("/index").authenticated()
                .antMatchers("/admin/**", "/add").hasRole("ADMIN")
                .regexMatchers("/admin1/.*").access("hasRole('ADMIN') or hasRole('ADMIN1')")
                .anyRequest().authenticated()
                .and()
                .requiresChannel().antMatchers("/add").requiresSecure().and().csrf().disable();//https://127.0.0.1:8443/add
//                .and()
//                .rememberMe().tokenValiditySeconds(2419200).tokenRepository(persistentTokenRepository());
    }

    //身份验证管理生成器
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //1.启用内存用户存储
//        auth.inMemoryAuthentication()
//                .withUser("xfy").password(passwordEncoder().encode("1234")).roles("ADMIN").and()
//                .withUser("tom").password(passwordEncoder().encode("1234")).roles("USER");
        //2.基于数据库表进行验证
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,enabled from user where username = ?")
//                .authoritiesByUsernameQuery("select username,rolename from role where username=?")
//                .passwordEncoder(passwordEncoder());
        //3.配置自定义的用户服务
        auth.userDetailsService(myUserDetailService);
    }

    //WEB安全
    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}