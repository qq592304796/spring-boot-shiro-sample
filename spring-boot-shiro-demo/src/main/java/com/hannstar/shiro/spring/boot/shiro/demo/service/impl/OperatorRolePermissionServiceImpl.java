package com.hannstar.shiro.spring.boot.shiro.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.OperatorRolePermission;
import com.hannstar.shiro.spring.boot.shiro.demo.mapper.OperatorRolePermissionMapper;
import com.hannstar.shiro.spring.boot.shiro.demo.service.IOperatorRolePermissionService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限关系表 服务实现类
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-04-02
 */
@Service
public class OperatorRolePermissionServiceImpl extends ServiceImpl<OperatorRolePermissionMapper, OperatorRolePermission> implements IOperatorRolePermissionService {

}
