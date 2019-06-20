package com.cloud.eurekaclient.user.controller;


import com.cloud.eurekaclient.redis.service.RedisService;
import com.cloud.eurekaclient.user.model.City;
import com.cloud.eurekaclient.user.service.CityService;
import com.cloud.eurekaclient.util.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @Autowired
    CityService cityService;
    /**
     * Object o = httpSession.getAttribute("springboot");
     * httpSession.setAttribute("springboot", o);
     * return "端口=" + request.getLocalPort() +  " sessionId=" + request.getSession().getId() +"<br/>"+o;
     */

    @Value("${server.port}")
    String port;

    // HttpServletRequest request
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String index(@RequestHeader("age") int age, HttpServletRequest request) {
        System.out.println("年龄为："+age);
        String result = sender.send();
        System.out.println(redisService.getCache("hello"));
        System.out.println("sessionID" +request.getSession().getId());
        City city = cityService.selectById(1L);
        System.out.println("city infomation:" + city);
        if (httpSession.getAttribute(request.getSession().getId()) != null){
            String res = "端口=" + request.getLocalPort() +  " sessionId=" + request.getSession().getAttribute(httpSession.getId()) +"<br/>";
            System.out.println("res值：：" + res);
        }else {
            httpSession.setAttribute(request.getSession().getId(),"user is Login");

        }
        Object o = httpSession.getAttribute("springboot");
        httpSession.setAttribute("springboot", o);
        redisService.putCache("hello","登陆成功");

        StringBuffer uriList = new StringBuffer("Hello World " + port + " 端口为您服务！<br>");
        return uriList.toString() + " ++++" + result;
    }
}
