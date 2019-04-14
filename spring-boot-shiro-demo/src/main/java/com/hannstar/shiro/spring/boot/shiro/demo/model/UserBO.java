package com.hannstar.shiro.spring.boot.shiro.demo.model;

import com.hannstar.shiro.spring.boot.shiro.demo.type.StatusEnum;

import lombok.Data;

/**
 * 
 * @author jiangxinjun
 * @date 2019-04-02
 */
@Data
public class UserBO {
    private Integer id;

    private String account;

    private String name;

    private String password;

    private StatusEnum status;

}
