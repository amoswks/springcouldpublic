package com.cloud.eurekaclient.redis.service;

import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 将java对象序列化后存入redis 不设置超时时间
     *
     * @param key
     * @param obj
     * @return
     */

    public boolean putCache(final String key, final Object obj) {
        try {
            return putCache(key, obj, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将java对象序列化后存入redis
     *
     * @param key
     * @param obj
     * @param time
     * @return
     */
    public boolean putCache(final String key, final Object obj, Long time) {
        try {
            if (time != null) {
                redisTemplate.opsForValue().set(key, obj, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, obj);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 得到redis缓存
     *
     * @param key
     * @return
     */
    public Object getCache(final String key) {
        Object obj = null;
        try {
            obj = redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);//设置超时时间
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取过期时间
     *
     * @param key
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在 * * @param key 键 * @return true 存在 false不存在
     *
     * @return
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除key缓存
     *
     * @param key
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 0) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /***************************************String类型****************************************/

    /**
     * 普通缓存放入 * * @param key 键 * @param value 值 * @return true成功 false失败
     */
    public boolean set(String key, Object obj) {
        try {
            redisTemplate.opsForValue().set(key, obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间 * * @param key 键 * @param value 值 * @param time 时间(秒) time要大于0
     * 如果time小于等于0 将设置无限期 * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增 * * @param key 键 * @param delta 要增加几(大于0) * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须小于0");
        }
        return redisTemplate.opsForValue().decrement(key,delta);

    }


    /***************************Map*****************************************/
    /**
     * 获取hashKey对应的所有键值 * * @param key 键 * @return 对应的多个键值
     */
    public Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }
    /**
     * HashSet * * @param key 键 * @param map 对应多个键值 * @return true 成功 false 失败
     */
    public boolean hmset(String key,Map<String,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * HashSet 并设置时间 * * @param key 键 * @param map 对应多个键值 * @param time 时间(秒)
     * * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String,Object> map , long time){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            if (time > 0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 向一张hash表中放入数据,如果不存在将创建 * * @param key 键 * @param item 项 * @param value 值
     * * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 删除hash表中的值 * * @param key 键 不能为null * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值 * * @param key 键 不能为null * @param item 项 不能为null * @return
     * true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回 * * @param key 键 * @param item 项 * @param by
     * 要增加几(大于0) * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减 * * @param key 键 * @param item 项 * @param by 要减少记(小于0) * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }
    // ============================set=============================

}



