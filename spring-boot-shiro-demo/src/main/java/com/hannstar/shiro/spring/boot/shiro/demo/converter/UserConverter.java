package com.hannstar.shiro.spring.boot.shiro.demo.converter;

import com.hannstar.shiro.spring.boot.shiro.demo.entity.OperatorUser;
import com.hannstar.shiro.spring.boot.shiro.demo.model.UserBO;
import com.hannstar.shiro.spring.boot.shiro.demo.type.StatusEnum;

import lombok.experimental.UtilityClass;


/**
 * 
 * @author jiangxinjun
 * @date 2018年8月14日
 *
 */
@UtilityClass
public class UserConverter {

    public static UserBO convertBO(OperatorUser userDO) {
        if(userDO == null){
            return null;
        }
        UserBO userBO = new UserBO();
        userBO.setId(userDO.getId());
        userBO.setAccount(userDO.getAccount());
        userBO.setPassword(userDO.getPassword());
        userBO.setName(userDO.getName());
        userBO.setStatus(StatusEnum.getEnum(userDO.getStatus()));
        return userBO;
    }

}
