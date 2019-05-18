package com.cloud.eurekaclient.util;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public String send(){
        String context = "Hello"+ new Date();
        Object obj = this.amqpTemplate.convertSendAndReceive("hello",context);
        return obj.toString();
    }
}
