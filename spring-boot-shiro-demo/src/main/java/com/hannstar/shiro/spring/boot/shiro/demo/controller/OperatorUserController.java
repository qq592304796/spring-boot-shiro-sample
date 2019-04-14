package com.hannstar.shiro.spring.boot.shiro.demo.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannstar.shiro.spring.boot.shiro.demo.converter.UserConverter;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.OperatorUser;
import com.hannstar.shiro.spring.boot.shiro.demo.model.UserBO;
import com.hannstar.shiro.spring.boot.shiro.demo.service.IOperatorUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 管理员信息 前端控制器
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-04-02
 */
@Api(tags = "user", description = "用户")
@Slf4j
@RestController
@RequestMapping("user")
public class OperatorUserController {
    
    @Autowired
    private IOperatorUserService operatorUserService;
    
    @ApiOperation(value = "获取单个用户信息", notes = "获取单个用户信息")
    @RequiresPermissions("user:list")
    @GetMapping(value = "/getUserById")
    public UserBO getUserById(@RequestParam Long userId) {
        OperatorUser userDO = operatorUserService.getById(userId);
        return UserConverter.convertBO(userDO);
    }
    
    @ApiOperation(value = "登录", notes = "登录")
    @GetMapping(value = "login")
    public void login(@RequestParam String account, @RequestParam String password) {
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(account, password, true));
        } catch (LockedAccountException lae) {
            log.error("账号已停用：" + lae);
        } catch (AuthenticationException e) {
            log.error("用户名或密码错误：" + e);
        }
    }
    
    @ApiOperation(value = "退出", notes = "退出")
    @RequiresAuthentication
    @DeleteMapping(value = "logout")
    public void logout() {
        SecurityUtils.getSubject().logout();
    }
}
