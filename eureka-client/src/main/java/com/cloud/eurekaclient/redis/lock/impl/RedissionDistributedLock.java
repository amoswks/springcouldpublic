package com.cloud.eurekaclient.redis.lock.impl;

import com.cloud.eurekaclient.redis.lock.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RedissionDistributedLock implements DistributedLock {

    private RedissonClient redissonClient;

    public RedissionDistributedLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public RLock lock(String lockKey) {
        return null;
    }

    @Override
    public RLock lock(String lockKey, long leaseTime) {
        return null;
    }

    @Override
    public RLock lock(String lockKey, long leaseTime, TimeUnit time) {
        return null;
    }

    @Override
    public RLock tryLock(String lockKey, long waitTime, long leadTime, TimeUnit time) {
        return null;
    }

    @Override
    public RLock unlock(String lockKey) {
        return null;
    }
}
