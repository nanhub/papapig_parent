package com.pppig.user.service.impl;

import com.pppig.user.dao.LoginDao;
import com.pppig.user.pojo.UserMain;
import com.pppig.user.service.LoginService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.MD5Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void InsertUserMain(UserMain userMain) {
        userMain.setPasswd(bCryptPasswordEncoder.encode(userMain.getPasswd()));
        userMain.setRegisterTime(new Date());
        userMain.setPayPassword("000000");
        userMain.setNickName(userMain.getMobile());
        userMain.setRoles(2);
        userMain.setCash(0.00);
        userMain.setFrozenWithDrawCash(0.00);
        userMain.setFrozenBiddingCash(0.00);
        userMain.setIdVerifyLimit(1);
        userMain.setStatus(0);
        userMain.setForbidStatus(0);
        userMain.setAlbumCapacity(50);
        userMain.setSecurityLevel(0);

        if(userMain.getReferee()==null || "".equals(userMain.getReferee())){
            userMain.setReferee("");
        }

        System.out.println(userMain.getPasswd());
        loginDao.InsertUserMain(userMain);
    }

    @Override
    public void sendSms(String mobile) {
        //1.生成6位短信验证码
        String code = RandomStringUtils.randomNumeric(6);

        //存放到redis
        redisTemplate.opsForValue().set("code_"+mobile,code,6, TimeUnit.HOURS);

        Map<String,String> map = new HashMap<>();
        map.put("code",code);
        map.put("mobile",mobile);
        //给用户发送短信
        //rabbitTemplate.convertAndSend("pig",map);
        //在控制台显示
        System.out.println(mobile+"收到验证码是："+code);
    }

    @Override
    public UserMain login(UserMain userMain) {
        UserMain login = loginDao.login(userMain);
        if(login!=null || bCryptPasswordEncoder.matches(userMain.getPasswd(),login.getPasswd())){
            return  login;
        }
        return null;
    }
}
