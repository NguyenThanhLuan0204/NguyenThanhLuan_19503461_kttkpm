package org.example.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisUtils {

    private static JedisPool jedisPool = null;
    private static String host = null;
    private static Integer port = 0;
    private static Integer maxTotal = 0;
    private static Integer maxIdle = 0;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("redis");
        host = resourceBundle.getString("redis.host");
        port = Integer.parseInt(resourceBundle.getString("redis.port"));
        maxTotal = Integer.parseInt(resourceBundle.getString("redis.maxTotal"));
        maxIdle = Integer.parseInt(resourceBundle.getString("redis.maxIdle"));


        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);                             // Set the maximum number of connections
        jedisPoolConfig.setMaxIdle(maxIdle);                              // Set the maximum number of active connections
        jedisPool = new JedisPool(jedisPoolConfig,host,port);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
