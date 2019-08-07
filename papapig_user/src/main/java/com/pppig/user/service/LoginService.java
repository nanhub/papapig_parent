package com.pppig.user.service;

import com.pppig.user.pojo.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface LoginService {


    /**
     * 注册
     * @param userMain
     */
    void InsertUserMain(UserMain userMain);

    /**
     * 发送信息
     * @param mobile
     */
    void sendSms(String mobile);

    /**
     * 登录
     * @param userMain
     * @return
     */
    UserMain login(UserMain userMain);
}
