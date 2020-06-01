package com.tensquare.sms.listenser;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsListenser {
    @RabbitHandler
    public void executSms(Map<String, String> map) {
        System.out.println("mobile:" + map.get("mobile"));
        System.out.println("checkCode" + map.get("checkCode"));
    }
}
