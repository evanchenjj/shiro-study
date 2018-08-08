package com.evancjj.shiro.session;

import com.evancjj.shiro.utils.RedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CustomSessionDao extends AbstractSessionDAO {

    private final String SHIRO_PREX="shiro_ssm:";

    @Resource
    private RedisUtil jedis;

    private byte[] getKey(String sessionId) {
        return (sessionId+SHIRO_PREX).getBytes();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        byte[] key =getKey(sessionId.toString());
        byte[] value = SerializationUtils.serialize(session);
        jedis.set(key,value);
        jedis.expire(key,600);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        if (serializable == null) {
            return null;
        }
        String sessionId = (String) serializable;
        byte[] key = getKey(sessionId);
        byte[] value =jedis.getValue(key);
        Session session = (Session) SerializationUtils.deserialize(value);
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        String sessionId =session.getId().toString();
        byte [] key=getKey(sessionId);
        byte [] value =SerializationUtils.serialize(session);
        jedis.set(key,value);
    }

    @Override
    public void delete(Session session) {
        String sessionId =session.getId().toString();
        byte [] key=getKey(sessionId);
        jedis.del(key);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<byte[]> values =jedis.values(SHIRO_PREX);
        if (CollectionUtils.isEmpty(values)) {
            return null;
        }
        Set<Session> sessionSet = new HashSet<>();
        for (byte[] v:values) {
            Session session= (Session) SerializationUtils.deserialize(v);
            sessionSet.add(session);
        }
        return sessionSet;
    }
}
