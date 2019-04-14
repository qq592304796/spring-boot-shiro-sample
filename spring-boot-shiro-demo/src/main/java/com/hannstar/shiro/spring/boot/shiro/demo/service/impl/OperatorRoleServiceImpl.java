package com.hannstar.shiro.spring.boot.shiro.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.OperatorRole;
import com.hannstar.shiro.spring.boot.shiro.demo.mapper.OperatorRoleMapper;
import com.hannstar.shiro.spring.boot.shiro.demo.service.IOperatorRoleService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-04-02
 */
@Service
public class OperatorRoleServiceImpl extends ServiceImpl<OperatorRoleMapper, OperatorRole> implements IOperatorRoleService {

}
