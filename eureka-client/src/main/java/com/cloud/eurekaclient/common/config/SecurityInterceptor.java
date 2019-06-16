package com.cloud.eurekaclient.common.config;

import com.alibaba.fastjson.JSON;
import com.cloud.eurekaclient.common.dto.BaseResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class SecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute(session.getId()) != null){
            return true;
        }
        response.getWriter().write(JSON.toJSONString(BaseResponse.success()));
        return false;
    }
}
