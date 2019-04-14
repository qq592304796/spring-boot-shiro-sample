package com.hannstar.shiro.spring.boot.shiro.demo.controller;


import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hannstar.shiro.spring.boot.shiro.demo.Result;

/**
 * 
 * @author jiangxinjun
 * @date 2019-04-13
 */
@RestController
@RequestMapping("/")
public class CommonController {
    
    
    @SuppressWarnings("rawtypes")
    @GetMapping(value = "unauthorized")
    public Result unauthorized() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setStatus(403);
        Result result = new Result();
        result.setRet(403);
        result.setMsg("未授权");
        return result;
    }
    
    @RequiresAuthentication
    @GetMapping("authorized")
    public String authorized() {
        System.out.println(SecurityUtils.getSubject().isRemembered());
        System.out.println(SecurityUtils.getSubject().isAuthenticated());
        return "success";
    }
    
    @GetMapping
    public String index() {
        System.out.println(SecurityUtils.getSubject().isRemembered());
        System.out.println(SecurityUtils.getSubject().isAuthenticated());
        return "success";
    }
}
