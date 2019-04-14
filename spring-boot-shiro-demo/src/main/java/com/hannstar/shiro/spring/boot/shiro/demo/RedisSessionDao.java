package com.hannstar.shiro.spring.boot.shiro.demo;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisSessionDao extends EnterpriseCacheSessionDAO {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        redisTemplate.opsForValue().set(sessionId.toString(), session, session.getTimeout(), TimeUnit.MILLISECONDS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return (Session) redisTemplate.opsForValue().get(sessionId.toString());
    }

    //设置session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
        redisTemplate.opsForValue().set(session.getId().toString(),session, session.getTimeout(), TimeUnit.MILLISECONDS);
    }

    // 删除session
    @Override
    protected void doDelete(Session session) {
        redisTemplate.delete(session.getId().toString());
    }

    /*
    private byte[] sessionToByte(Session session){
        if (null == session){
            return null;
        }
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] bytes = null;
        ObjectOutputStream oo ;
        try {
            oo = new ObjectOutputStream(bo);
            oo.writeObject(session);
            bytes = bo.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bytes;

    }
    private Session byteToSession(byte[] bytes){
        if(0==bytes.length){
            return null;
        }
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream in;
        SimpleSession session = null;
        try {
            in = new ObjectInputStream(bi);
            session = (SimpleSession) in.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return session;
    }
    */
}