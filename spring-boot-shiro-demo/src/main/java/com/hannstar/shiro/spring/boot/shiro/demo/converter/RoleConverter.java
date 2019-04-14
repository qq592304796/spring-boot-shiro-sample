package com.hannstar.shiro.spring.boot.shiro.demo.converter;


import com.hannstar.shiro.spring.boot.shiro.demo.entity.OperatorRole;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.extend.UserRoleDOView;
import com.hannstar.shiro.spring.boot.shiro.demo.model.RoleBO;

import lombok.Data;

/**
 * 
 * @author jiangxinjun
 * @date 2018年8月14日
 *
 */
@Data
public class RoleConverter {

    public static RoleBO coverBO(UserRoleDOView userRoleDOView) {
        if (userRoleDOView == null) {
            return null;
        }
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleName(userRoleDOView.getRoleName());
        roleBO.setRoleKey(userRoleDOView.getRoleKey());
        roleBO.setId(userRoleDOView.getId());
        return roleBO;
    }

    public static RoleBO cover(OperatorRole roleDO) {
        if (roleDO == null) {
            return null;
        }
        RoleBO roleBO = new RoleBO();
        roleBO.setId(roleDO.getId());
        roleBO.setRoleName(roleDO.getRoleName());
        roleBO.setRoleKey(roleDO.getRoleKey());
        roleBO.setGmtCreate(roleDO.getGmtCreate());
        return roleBO;
    }

}
