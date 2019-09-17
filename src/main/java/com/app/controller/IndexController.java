package com.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.app
 * @ClassName Controller
 * @Author shaobin.wang
 * @Date 2019/09/16 18:08
 * @Version 1.0
 * @Description:
 **/
@RestController
@Slf4j
public class IndexController {

    @GetMapping("/api/ok/1")
    public String ok() {
        return "ok";
    }



    @GetMapping("index")
    public String no() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object details = auth.getDetails();
        User principal = (User) auth.getPrincipal();
        log.info("数据{}，{}", details);
        log.info("登录信息P {}", principal.getUsername());
        return "index";
    }


    @GetMapping("hello")
    public String hello() {

        return "hello";
    }
}
