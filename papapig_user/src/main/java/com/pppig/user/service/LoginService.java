package com.pppig.user.service;

import com.pppig.user.pojo.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface LoginService {


    void InsertUserMain(UserMain userMain);

    void sendSms(String mobile);
}
