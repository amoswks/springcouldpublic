package com.cloud.eurekaclient.util;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 队列。键值
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {
    @RabbitHandler
    public String process(String hello){
        System.out.println("reviex  "+ hello);
        return "success,RabbitMQ处理消息成功！";
    }
}
