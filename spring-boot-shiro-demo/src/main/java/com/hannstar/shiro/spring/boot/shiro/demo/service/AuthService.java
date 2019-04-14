package com.hannstar.shiro.spring.boot.shiro.demo.service;

import com.hannstar.shiro.spring.boot.shiro.demo.model.ShiroUser;

/**
 * @author Leach
 * @date 2017/4/18
 */
public interface AuthService {

    ShiroUser find(String account);

    ShiroUser find(String account, String password);
}
