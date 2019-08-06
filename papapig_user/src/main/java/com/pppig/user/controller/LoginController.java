package com.pppig.user.controller;

import com.pppig.user.pojo.UserMain;
import com.pppig.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

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

    //发送验证码
    @RequestMapping("sendSms")
    @ResponseBody
    public String sendSms(String mobile){
        if(mobile==null || mobile.equals("")){
            return "no";
        }
        loginService.sendSms(mobile);
        return "success";
    }

    //注册
    @RequestMapping("register")
    public String register(UserMain userMain){
        loginService.InsertUserMain(userMain);
        return "1";
    }

}
