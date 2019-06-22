package com.cloud.eurekaclient.redis.lock;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

public interface DistributedLock {
    /**
     * 获取锁
     * @param lockKey
     * @return
     */
    RLock lock(String lockKey);

    /**
     * 获取锁，并设置锁的超时时长
     * @param lockKey
     * @param leaseTime
     * @return
     */
    RLock lock(String lockKey,long leaseTime);

    /**
     * 获取锁，并设置锁的超时时长
     * @param lockKey
     * @param leaseTime
     * @param time
     * @return
     */
    RLock lock(String lockKey, long leaseTime, TimeUnit time);

    /**
     * 尝试获取锁
     * @param lockKey
     * @param waitTime
     * @param leadTime
     * @param time
     * @return
     */
    RLock tryLock(String lockKey, long waitTime,long leadTime,TimeUnit time);

    /**
     * 释放锁
     * @param lockKey
     * @return
     */
    RLock unlock(String lockKey);
}
