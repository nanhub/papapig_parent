package com.pppig.user.controller;


import com.pppig.user.pojo.UserMain;
import com.pppig.user.service.UserService;

import common.Common;
import common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private RedisTemplate redisTemplate;


    //我的账户
    @RequestMapping("home")
    public String home(HttpSession session){
        //获取session中的值
        Integer userId = (Integer) session.getAttribute("userId");

        //根据userId查询对象
        UserMain user = userService.findOne(userId);

        //判断是否登录
        if(user!=null && user.getUserId().equals(userId)){
            return "accountCenter/home";
        }
        return "redirect:/toLogin";

    }

    //查询是否开户
    @RequestMapping("findUserCode")
    @ResponseBody
    public String findUserCode(HttpSession session){
        //获取session中的值
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId!=null ){
            UserMain user = userService.findOne(userId);
            if(user.getUserCode()!=null && user.getUserCode().equals("")){
                return "success";
            }else{
                return "" ;
            }
        }else{
            return "no";
        }

    }

    //开户页面
    @RequestMapping("accountOpen")
    public String accountOpen(){
        return "accountCenter/pppigAccountOpen";
    }

    //开户
    @RequestMapping("getOpenAccount")
    public String getOpenAccount(String idCardNo,String name,String mobile,HttpServletRequest request,HttpSession session ){

        HashMap<String, String> OPMap = new HashMap<>();
        //判断是否传值
        if ("".equals(idCardNo)&&"".equals(name)&&"".equals(mobile)){
            request.setAttribute("no","输入信息有误");
            return "accountCenter/pppigAccountOpen";
        }
        //电话号验证
        Pattern p = Pattern.compile("^1[0-9]{10}$");
        Matcher m = p.matcher(mobile);
        boolean b = m.matches();
        if(!b){
            request.setAttribute("no","手机号格式错误");;
            return "accountCenter/pppigAccountOpen";
        }
        //身份证校验
        String regex = "\\d{15}(\\d{2}[0-9xX])?";
        boolean matches = idCardNo.matches(regex);
        if(!matches){
            request.setAttribute("no","身份证格式错误");;
            return "accountCenter/pppigAccountOpen";
        }

        //获取session中的userid
        Integer userId = (Integer) session.getAttribute("userId");
        OPMap.put("userId",userId.toString());
        OPMap.put("idCardNo", idCardNo);
        OPMap.put("name", name);
        OPMap.put("mobile", mobile);
        Map<String, String> OPSMap = userService.accountOpen(OPMap);

        //若返回的集合有值则提示用户相对应的提示
        String no = OPSMap.get("no");
        if(no!=null){
            request.setAttribute("no",no);
            return "accountCenter/pppigAccountOpen";
        }
        request.setAttribute("OPMap",OPMap);

        return "accountCenter";
    }




}
