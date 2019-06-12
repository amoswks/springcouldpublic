package com.cloud.eurekaclient.controller;


import com.cloud.eurekaclient.redis.service.RedisService;
import com.cloud.eurekaclient.util.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private Sender sender;

    @Autowired
    HttpSession httpSession;

    @Autowired
    RedisService redisService;
    /**
     * Object o = httpSession.getAttribute("springboot");
     * httpSession.setAttribute("springboot", o);
     * return "端口=" + request.getLocalPort() +  " sessionId=" + request.getSession().getId() +"<br/>"+o;
     */

    @Value("${server.port}")
    String port;

    // HttpServletRequest request
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String index() {
        String result = sender.send();
        System.out.println(redisService.getCache("hello"));
        Object o = httpSession.getAttribute("springboot");
        httpSession.setAttribute("springboot", o);
        redisService.putCache("hello","登陆成功");

        //  return "端口=" + request.getLocalPort() +  " sessionId=" + request.getSession().getId() +"<br/>"+o;
        StringBuffer uriList = new StringBuffer("Hello World " + port + " 端口为您服务！<br>");
        return uriList.toString() + " ++++" + result;
    }
}
