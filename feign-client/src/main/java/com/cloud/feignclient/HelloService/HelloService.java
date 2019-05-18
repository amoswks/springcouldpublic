package com.cloud.feignclient.HelloService;

 import com.cloud.feignclient.HelloServerImp.SchedualSeveiceHiHyHytrix;
 import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "eureka-client",fallback = SchedualSeveiceHiHyHytrix.class)
public interface HelloService {
    @RequestMapping(value = "/hello")
    String hello();
}
