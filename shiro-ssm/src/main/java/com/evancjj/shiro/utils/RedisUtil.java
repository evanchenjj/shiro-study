package com.evancjj.shiro.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class RedisUtil {

    @Resource
    private JedisPool jedisPool;

    private Jedis getResource(){
        return jedisPool.getResource();
    }

    public void set(byte[] key, byte[] value) {
        Jedis jedis =getResource();
        try {
            jedis.set(key,value);
        } finally {
            jedis.close();
        }
    }

    public byte[] getValue(byte[] key) {
        Jedis jedis =getResource();
        try {
             return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    public void del(byte[] key) {
        Jedis jedis =getResource();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public Set<byte[]> keys(String shiro_prex) {
        Jedis jedis =getResource();
        try {
            return jedis.keys((shiro_prex + "*").getBytes());
        } finally {
            jedis.close();
        }
    }

    public Set<byte[]> values(String shiro_prex) {
        Jedis jedis =getResource();
        Set<byte[]> values = new HashSet<>();
        try {
            Set<byte[]> keySet= jedis.keys((shiro_prex + "*").getBytes());
            for (byte[] bytes : keySet) {
                byte [] value=getValue(bytes);
                values.add(value);
            }
            return values;
        } finally {
            jedis.close();
        }
    }

    public void expire(byte[] key, int i) {
        Jedis jedis =getResource();
        try {
            jedis.expire(key,i);
        } finally {
            jedis.close();
        }
    }
}
