package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Package com.app.controller
 * @ClassName LoginController
 * @Author shaobin.wang
 * @Date 2019/09/17 16:07
 * @Version 1.0
 * @Description:
 **/
@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "redirect:/index";
    }

    @GetMapping("signIn")
    public String signIn(@RequestParam(value = "msg", required = false) String msg, ModelMap modelMap) {

        if (!StringUtils.isEmpty(msg)) {
            modelMap.addAttribute("msg", "登入失败");
        }

        return "signIn";
    }
}
