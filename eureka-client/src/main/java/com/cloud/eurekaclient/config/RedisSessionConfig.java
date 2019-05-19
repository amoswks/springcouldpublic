package com.cloud.eurekaclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 用于指定sess的过期时间，默认是30分钟，
 * 可以按系统的实际情况改写maxInactiveIntervalInSeconds，
 * 设置session过期时间。
 * HttpSession
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class RedisSessionConfig {
}
