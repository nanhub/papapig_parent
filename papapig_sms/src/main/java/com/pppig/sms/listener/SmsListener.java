package com.pppig.sms.listener;



import com.pppig.sms.util.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 短信监听类
 */
@Component
@RabbitListener(queues = "pig")
public class SmsListener {




    /**
     * 发送短信
     * @param message
     */
    @RabbitHandler
    public void sendSms(Map<String,String> message){
        String mobile = message.get("mobile");
        String code = message.get("code");
        System.out.println("手机号："+mobile);
        System.out.println("验证码："+code);
        try {
            SmsUtil.sendSms(mobile,code);
        }finally {

        }


    }
}
