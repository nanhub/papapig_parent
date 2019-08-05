package com.pppig.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    //首页
    @RequestMapping("index")
    public String tologin(){
        return "index";
    }

    //注册页面
    @RequestMapping("toRegister")
    public String toRegister(){
        return "register";
    }

    //登录页面
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

}
