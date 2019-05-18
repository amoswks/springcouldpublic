package com.cloud.feignclient.HelloServerImp;

import com.cloud.feignclient.HelloService.HelloService;
import org.springframework.stereotype.Component;

@Component
public class SchedualSeveiceHiHyHytrix implements HelloService {
    @Override
    public String hello() {
        return "出错啦～";
    }
}
