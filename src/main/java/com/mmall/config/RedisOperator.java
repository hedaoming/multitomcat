package com.mmall.config;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@Slf4j
public class RedisOperator {

    public static String set(String key, String value){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = JedisPoolUtil.getResources();
            result = jedis.set(key, value);
        }catch (Exception e){
            log.error("set fail", e);
        }finally {
            if (jedis != null){
                JedisPoolUtil.returnResources(jedis);
            }
        }
        return result;
    }

    public static String get(String key){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = JedisPoolUtil.getResources();
            result = jedis.get(key);
        }catch (Exception e){
            log.error("get fail", e);
        }finally {
            if (jedis != null){
                JedisPoolUtil.returnResources(jedis);
            }
        }
        return result;
    }

    public static Long deleteKey(String key){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = JedisPoolUtil.getResources();
            result = jedis.del(key);
        }catch (Exception e){
            log.error("del fail", e);
        }finally {
            if (jedis != null){
                JedisPoolUtil.returnResources(jedis);
            }
        }
        return result;
    }

    /**
     * 获取锁：获取锁失败时，需判断时间戳是否有效，无效则重新设置锁
     * @param key
     * @param timeout
     * @param timestamp
     * @return
     */
    public static Long lock(String key,int timeout, String timestamp){
        Jedis jedis = JedisPoolUtil.getResources();
        Long result = jedis.setnx(key, timestamp);
        if (result.intValue() > 0){
            jedis.expire(key, timeout);
            return result;
        }else {
            // 获取不到锁，则校验时间戳
            String oldTimeStamp = jedis.get(key);
            Long oldTime = Long.parseLong(oldTimeStamp);
            // 锁已经过期了：和之前get出来的时间戳对比，若和设置新值后的时间戳不相等，证明已经被其他线程设置获取到锁了
            if (oldTime > System.currentTimeMillis()){
                String newTimeStamp = jedis.getSet(key, String.valueOf(System.currentTimeMillis() + timeout * 1000));
                if (newTimeStamp == null || (newTimeStamp != null && newTimeStamp.equals(oldTimeStamp))){
                    jedis.expire(key, timeout);
                    return new Long(1);
                }
            }
            return new Long(0);
        }
    }
}
