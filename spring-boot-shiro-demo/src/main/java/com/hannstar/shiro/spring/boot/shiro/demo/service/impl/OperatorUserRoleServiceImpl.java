package com.hannstar.shiro.spring.boot.shiro.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.OperatorUserRole;
import com.hannstar.shiro.spring.boot.shiro.demo.mapper.OperatorUserRoleMapper;
import com.hannstar.shiro.spring.boot.shiro.demo.service.IOperatorUserRoleService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-04-02
 */
@Service
public class OperatorUserRoleServiceImpl extends ServiceImpl<OperatorUserRoleMapper, OperatorUserRole> implements IOperatorUserRoleService {

}
