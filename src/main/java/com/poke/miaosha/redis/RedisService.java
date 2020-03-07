package com.poke.miaosha.redis;

import com.alibaba.fastjson.JSON;
import com.poke.miaosha.domain.MiaoshaOrder;
import com.poke.miaosha.domain.MiaoshaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ClassName RedisService
 * @Description //TODO
 * @Author poke
 * @Date 2020/2/29 8:50 下午
 */
@Service
public class RedisService {
    @Autowired
    JedisPool jedisPool;

    /**
     * @Description 获取单个对象
     *
     * @param prefix
     * @param key
     * @param clazz
     * @return T
     **/
    public  <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * @Description 设置对象
     *
     * @param prefix
     * @param key
     * @param value
     * @return boolean
     **/
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.expireSeconds();
            if (seconds <= 0 ) {
                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * @Description 判断是否存在
     *
     * @param prefix
     * @param key
     * @return boolean
     **/
    public <T> boolean exits(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * @Description 增加值
     *
     * @param prefix
     * @param key
     * @return java.lang.Long
     **/
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * @Description 删除缓存
     *
     * @param prefix
     * @param key
     * @return boolean
     **/
    public boolean delete(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            long ret = jedis.del(realKey);
            return ret > 0;
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * @Description 减少值
     *
     * @param prefix
     * @param key
     * @return java.lang.Long
     **/
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        }else if (clazz == String.class) {
            return (T)str;
        }else if (clazz == long.class || clazz == Long.class) {
            return (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        }else if (clazz == String.class) {
            return (String) value;
        }else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        }else {
            return JSON.toJSONString(value);
        }
    }
}
