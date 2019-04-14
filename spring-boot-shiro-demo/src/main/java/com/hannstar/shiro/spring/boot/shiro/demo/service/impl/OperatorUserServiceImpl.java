package com.hannstar.shiro.spring.boot.shiro.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hannstar.shiro.spring.boot.shiro.demo.converter.RoleConverter;
import com.hannstar.shiro.spring.boot.shiro.demo.converter.UserConverter;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.OperatorUser;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.extend.RolePermissionDOView;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.extend.UserRoleDOView;
import com.hannstar.shiro.spring.boot.shiro.demo.mapper.OperatorRolePermissionMapper;
import com.hannstar.shiro.spring.boot.shiro.demo.mapper.OperatorUserMapper;
import com.hannstar.shiro.spring.boot.shiro.demo.mapper.OperatorUserRoleMapper;
import com.hannstar.shiro.spring.boot.shiro.demo.model.PerssionInfoListBO;
import com.hannstar.shiro.spring.boot.shiro.demo.model.RoleBO;
import com.hannstar.shiro.spring.boot.shiro.demo.model.UserBO;
import com.hannstar.shiro.spring.boot.shiro.demo.service.IOperatorUserService;
import com.hannstar.shiro.spring.boot.shiro.demo.strategy.PasswordStrategy;
import com.hannstar.shiro.spring.boot.shiro.demo.type.StatusEnum;

/**
 * <p>
 * 管理员信息 服务实现类
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-04-02
 */
@Service
public class OperatorUserServiceImpl extends ServiceImpl<OperatorUserMapper, OperatorUser> implements IOperatorUserService {
    
    @Autowired
    private OperatorUserMapper operatorUserMapper;

    @Autowired
    private OperatorUserRoleMapper userRoleDOMapperExtend;

    @Autowired
    private OperatorRolePermissionMapper rolePermissionDOMapperExtend;

    @Autowired
    private PasswordStrategy passwordStrategy;

    @Override
    public UserBO find(String account, String pwd) {
        QueryWrapper<OperatorUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account).eq("password", passwordStrategy.encode(pwd));
        List<OperatorUser> userDOS = operatorUserMapper.selectList(queryWrapper);
        return userDOS.isEmpty() ? null : UserConverter.convertBO(userDOS.get(0));
    }

    @Override
    public List<RoleBO> findUserRoleByUserId(Integer userId) {

        List<UserRoleDOView> userRoleDOViews = userRoleDOMapperExtend.findUserRoleByUserId(userId);
        if (userRoleDOViews.isEmpty()) {
            return new ArrayList<>();
        }
        List<RoleBO> roleBOS = new ArrayList<>();
        for (UserRoleDOView userRoleDOView : userRoleDOViews) {
            RoleBO roleBO = RoleConverter.coverBO(userRoleDOView);
            roleBOS.add(roleBO);
        }
        return roleBOS;
    }

    @Override
    public List<String> findRolePermission(Integer roleId) {
        List<RolePermissionDOView> rolePermissionDOViews = rolePermissionDOMapperExtend.findRolePermission(roleId);
        if (rolePermissionDOViews.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> permissionKeys = new ArrayList<>();
        for (RolePermissionDOView rolePermissionDOView : rolePermissionDOViews) {
            permissionKeys.add(rolePermissionDOView.getPermissionKey());
        }
        return permissionKeys;
    }

    @Override
    public UserBO find(String account) {
        QueryWrapper<OperatorUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account).eq("status", StatusEnum.STATUS_VALID.getVal());
        List<OperatorUser> userDOS = operatorUserMapper.selectList(queryWrapper);
        return userDOS.isEmpty() ? null : UserConverter.convertBO(userDOS.get(0));
    }

    @Override
    public List<PerssionInfoListBO> findRolePermissionList(Integer userId) {

        List<UserRoleDOView> userRoleDOViews = userRoleDOMapperExtend.findUserRoleByUserId(userId);
        if (userRoleDOViews.isEmpty()) {
            return null;
        }
        List<Integer> roleids = new ArrayList<>();
        for (UserRoleDOView userRoleDOView : userRoleDOViews) {
            roleids.add(userRoleDOView.getId());
        }
        List<RolePermissionDOView> rolePermissionDOViews = rolePermissionDOMapperExtend.findRolePermissionList(roleids);
        if (rolePermissionDOViews.isEmpty()) {
            return new ArrayList<>();
        }
        List<PerssionInfoListBO> listBOS = new ArrayList<>();
        for (RolePermissionDOView rolePermissionDOView : rolePermissionDOViews) {
            PerssionInfoListBO perssionInfoListBO = new PerssionInfoListBO();
            perssionInfoListBO.setId(rolePermissionDOView.getId());
            perssionInfoListBO.setParentId(rolePermissionDOView.getParentId());
            perssionInfoListBO.setPermissionKey(rolePermissionDOView.getPermissionKey());
            perssionInfoListBO.setPermissionName(rolePermissionDOView.getPermissionName());
            perssionInfoListBO.setPermissionUrl(rolePermissionDOView.getPermissionUrl());
            listBOS.add(perssionInfoListBO);
        }
        return listBOS;
    }
    
}
