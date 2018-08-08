package com.evancjj.shiro.cache;

import com.evancjj.shiro.utils.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomCache<K,V> implements Cache<K,V> {

    private final String CACHE_PREX="shiro_cache";

    private byte[] getKey(K k){
        return SerializationUtils.serialize(k+CACHE_PREX);
    }

    @Resource
    private RedisUtil jedis;

    @Override
    public V get(K k) throws CacheException {
        byte [] key =getKey(k);
        byte [] value =jedis.getValue(key);
        V v= (V) SerializationUtils.deserialize(value);
        return v;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        byte [] key =getKey(k);
        byte [] value=SerializationUtils.serialize(v);
        jedis.set(key,value);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        byte [] key =getKey(k);
        byte [] value=jedis.getValue(key);
        jedis.del(key);
        V v = (V) SerializationUtils.deserialize(value);
        return v;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        Set<byte[]> values =jedis.keys(CACHE_PREX);
        Set<K> keySet = new HashSet<>();
        for (byte[] value : values) {
           K k = (K) SerializationUtils.deserialize(value);
           keySet.add(k);
        }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        Set<byte[]> values =jedis.values(CACHE_PREX);
        if (CollectionUtils.isEmpty(values)) {
            return null;
        }
        Set<V> vSet = new HashSet<>();
        for (byte[] v : values) {
            V v1= (V) SerializationUtils.deserialize(v);
            vSet.add(v1);
        }
        return vSet;
    }
}
