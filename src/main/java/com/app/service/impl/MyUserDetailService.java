package com.app.service.impl;

import com.app.dao.UserInfoDao;
import com.app.entity.UserInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package com.app.service.impl
 * @ClassName MyUserDetailService
 * @Author shaobin.wang
 * @Date 2019/09/17 15:57
 * @Version 1.0
 * @Description:
 **/
@Component
@Slf4j
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoDao userInfoDao;

    //    @Autowired
//    private RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserInfoEntity user = userInfoDao.findByUsername(name);
         if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("authError");
        }
        log.info("{}", user);
//        List<Role> role = roleRepository.findByUsername(name);
//        log.info("{}",role);
        List<GrantedAuthority> authorities = new ArrayList<>();
//        role.forEach(role1 -> authorities.addAll(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+role1.getRolename())));
//        log.info("{}",authorities);
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassWord() , authorities);
    }
}
