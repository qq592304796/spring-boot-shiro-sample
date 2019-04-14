package com.hannstar.shiro.spring.boot.shiro.demo.strategy;

import org.springframework.stereotype.Service;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
@Service
public class MD5PasswordStrategy implements PasswordStrategy {
    @Override
    public String encode(String originalPassword) {
        return MD5.MD5Encode(originalPassword);
    }
    
   
}
