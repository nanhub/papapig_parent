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
    public String getOpenAccount(String idCardNo,String name,String mobile,HttpServletRequest request ){
        Common common = new Common();
        if ("".equals(idCardNo)&&"".equals(name)&&"".equals(mobile)){
            request.setAttribute("error","输入信息有误");
            return "OpenAccount";
        }
        //三码校验
        //PhonecodeUtil.gethone(idCardNo, name, mobile);
        HashMap<String, String> OPMap = new HashMap<>();
        OPMap.put("version", Constant.version);
        OPMap.put("txCode", Constant.txCode);
        OPMap.put("instCode", Constant.instCode);
        OPMap.put("bankCode", Constant.bankCode);
        OPMap.put("txDate", common.todate());
        OPMap.put("txTime", common.totime());
        OPMap.put("seqNo", common.Random());
        OPMap.put("channel", Constant.channel);
        OPMap.put("acctUse", Constant.acctUse);
        OPMap.put("idType", Constant.idType);
        OPMap.put("idCardNo", idCardNo);
        OPMap.put("name", name);
        OPMap.put("mobile", mobile);
        OPMap.put("email", "");
        OPMap.put("retUrl", "index");
        OPMap.put("notifyUrl", "10.1.67.28:9008/ElectronicAccount");
        OPMap.put("userIP", "");
        OPMap.put("acqRes", "");
        request.setAttribute("OPMap",OPMap);
        return "accountCenter/accountOpen";
    }




}
