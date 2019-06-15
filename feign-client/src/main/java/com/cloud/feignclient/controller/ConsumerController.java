package com.cloud.feignclient.controller;


import com.cloud.feignclient.HelloService.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {

    @Autowired(required = false)
    HelloService helloService;

    @RequestMapping(value = "feign-consumer/{age}", method = RequestMethod.GET)
    public String helloConsumer(@PathVariable("age") int age) {
        return helloService.hello(age);
    }
}
