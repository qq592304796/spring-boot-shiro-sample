package com.hannstar.shiro.spring.boot.shiro.demo.config.redis;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 
 * @author jiangxinjun
 * @date 2019-04-13
 */
@Configuration
public class RedisConfig {
    
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    
    @SuppressWarnings({ "rawtypes" })
    @PostConstruct
    public RedisTemplate redisTemplate() {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        return redisTemplate;
    }
}
