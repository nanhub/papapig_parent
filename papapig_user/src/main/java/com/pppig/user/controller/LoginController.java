package com.pppig.user.controller;

import com.pppig.user.pojo.UserMain;
import com.pppig.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.AuthImageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisTemplate redisTemplate;

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
    @ResponseBody
    public String register(UserMain userMain,String code){
        String smsRedis = (String) redisTemplate.opsForValue().get("code_"+userMain.getMobile());
        if(smsRedis.isEmpty()){
            return "no";
        }
        if(!smsRedis.equals(code)){
            return "error";
        }
        loginService.InsertUserMain(userMain);
        return "success";
    }

    //随机验证码图片
    @RequestMapping("getMa")
    public void getMa(HttpServletRequest request, HttpServletResponse response){
        AuthImageController authImageController = new AuthImageController();
        authImageController.createImage(request, response);
    }

    //登录
    @RequestMapping("login")
    @ResponseBody
    public String login(UserMain userMain, String rand, HttpSession session){

        UserMain login = loginService.login(userMain);

        if(login!= null){
            session.setAttribute("userId",login.getUserId());
            //获取验证码
            String code = (String) session.getAttribute("rand");
            if(rand.equals(code)){
                return "ok";
            }else{
                return "";
            }
        }else{
            return "no";
        }



    }

}
