package com.cloud.eurekaclient.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class WebLogAcpect {
    /**
     * 1、*：匹配所有的字符
     * 2、..:一般用于匹配多个包，多个参数
     * 3、+：表示类及其子类
     *
     */
    @Pointcut("execution(public * com.cloud.eurekaclient..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void watchBeforePerformance(JoinPoint joinPoint) throws Exception {
        String class_name = joinPoint.getSignature().getName();
        log.info("class_name：{}被执行", class_name);
    }

    @After("webLog()")
    public void watchAfterPerformance(JoinPoint joinPoint) throws Exception {
        String class_name = joinPoint.getSignature().getName();
        log.info("class名称:{},执行完毕", class_name);
    }

    @AfterReturning(value = "webLog()", returning = "result")
    public void watchAfterReturningPerformance(JoinPoint joinPoint, Object result) throws Exception {
        String methodName = joinPoint.getSignature().getName();
        log.info("class名称:{},返回{}", methodName,result);
    }

    @AfterThrowing(value = "webLog()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        log.info("class名称:{},抛出异常{}", methodName,e);
    }
}