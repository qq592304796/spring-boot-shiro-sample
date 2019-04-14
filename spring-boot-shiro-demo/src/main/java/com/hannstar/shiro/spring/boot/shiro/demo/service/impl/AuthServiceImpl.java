package com.hannstar.shiro.spring.boot.shiro.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.LockedAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannstar.shiro.spring.boot.shiro.demo.model.OperatorUserDTO;
import com.hannstar.shiro.spring.boot.shiro.demo.model.RoleBO;
import com.hannstar.shiro.spring.boot.shiro.demo.model.ShiroRole;
import com.hannstar.shiro.spring.boot.shiro.demo.model.ShiroUser;
import com.hannstar.shiro.spring.boot.shiro.demo.model.UserBO;
import com.hannstar.shiro.spring.boot.shiro.demo.model.UserRoleDTO;
import com.hannstar.shiro.spring.boot.shiro.demo.service.AuthService;
import com.hannstar.shiro.spring.boot.shiro.demo.service.IOperatorUserService;
import com.hannstar.shiro.spring.boot.shiro.demo.type.StatusEnum;


/**
 * @author Leach
 * @date 2017/4/18
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private IOperatorUserService userService;

    @Override
    public ShiroUser find(String account) {

        UserBO userBO = userService.find(account);
        if (userBO == null) {
            return null;
        }
        OperatorUserDTO userDTO = getUserRoleKey(userBO.getId());

        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setAccount(userBO.getAccount());
        shiroUser.setPassword(userBO.getPassword());
        shiroUser.setRolesKey(userDTO.getRolesKey());
        List<ShiroRole> roles = new ArrayList<>();
        for (UserRoleDTO userRoleDTO : userDTO.getRoles()) {
            ShiroRole shiroRole = new ShiroRole();
            shiroRole.setRoleKey(userRoleDTO.getRoleKey());
            shiroRole.setPermissionsKey(userRoleDTO.getPermissionsKey());
            roles.add(shiroRole);
        }
        shiroUser.setRoles(roles);
        shiroUser.setRolesKey(userDTO.getRolesKey());
        return shiroUser;
    }

    @Override
    public ShiroUser find(String account, String password) {
        UserBO userBO = userService.find(account, password);
        if (userBO == null) {
            return null;
        }

        if (userBO.getStatus() != StatusEnum.STATUS_VALID) {
            throw new LockedAccountException();
        }

        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setAccount(userBO.getAccount());
        shiroUser.setPassword(userBO.getPassword());
        OperatorUserDTO userDTO = getUserRoleKey(userBO.getId());
        shiroUser.setRolesKey(userDTO.getRolesKey());
        List<ShiroRole> roles = new ArrayList<>();
        for (UserRoleDTO userRoleDTO : userDTO.getRoles()) {
            ShiroRole shiroRole = new ShiroRole();
            shiroRole.setPermissionsKey(userRoleDTO.getPermissionsKey());
            shiroRole.setRoleKey(userRoleDTO.getRoleKey());
            roles.add(shiroRole);
        }
        shiroUser.setRoles(roles);
        shiroUser.setRolesKey(userDTO.getRolesKey());
        return shiroUser;
    }


    private OperatorUserDTO getUserRoleKey(Integer userId) {
        List<RoleBO> roleBOList = userService.findUserRoleByUserId(userId);
        List<UserRoleDTO> userRoleDTOS = new ArrayList<>();
        Set<String> rolesKeys = new HashSet<>();
        UserRoleDTO userRoleDTO;
        if (!roleBOList.isEmpty()) {
            //查询role对应的permission_key
            for (RoleBO roleBO : roleBOList) {
                rolesKeys.add(roleBO.getRoleKey());
                userRoleDTO = new UserRoleDTO();
                userRoleDTO.setRoleKey(roleBO.getRoleKey());
                List<String> permissionKeyList = userService.findRolePermission(roleBO.getId());
                userRoleDTO.setPermissionsKey(permissionKeyList);
                userRoleDTOS.add(userRoleDTO);
            }
        }
        OperatorUserDTO userDTO = new OperatorUserDTO();
        userDTO.setRoles(userRoleDTOS);
        userDTO.setRolesKey(rolesKeys);

        return userDTO;
    }

}
