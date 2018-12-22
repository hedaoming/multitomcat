package com.mmall.config;

import com.mmall.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {

    private static JedisPool jedisPool = null;
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.maxTotal", "20"));//最大连接数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.maxIdle", "10"));//最大空闲连接数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.mixIdle", "5"));// 最小空间连接数
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.restOnBorrow"));// 在获取连接前，为true先验证连接有效，获取到的连接一定是有效的
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.testOnReturn"));// 在放入连接时，为true时先连接有效才放入
    private static String host = PropertiesUtil.getProperty("redis.host");
    private static Integer port = Integer.parseInt(PropertiesUtil.getProperty("redis.port", "20"));

    private static void init(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setBlockWhenExhausted(true);//连接耗尽时，是否会诸塞，true时会阻塞知道超时
        jedisPool = new JedisPool(config, host, port);
    }

    static {
        init();
    }

    public static Jedis getResources(){
        return jedisPool.getResource();
    }

    public static void returnResources(Jedis jedis){
        if (jedis != null) {
            jedis.close();
        }
    }

    public static void main(String args[]){
        Jedis jedis = getResources();
        jedis.set("leo", "testValue");
        String value = jedis.get("leo");
        System.out.println(value);
        jedis.flushDB();
        value = jedis.get("leo");
        System.out.println("删除values, values:" + value);
        returnResources(jedis);
    }
}
