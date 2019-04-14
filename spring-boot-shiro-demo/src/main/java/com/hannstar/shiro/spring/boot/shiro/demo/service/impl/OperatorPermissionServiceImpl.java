package com.hannstar.shiro.spring.boot.shiro.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.OperatorPermission;
import com.hannstar.shiro.spring.boot.shiro.demo.mapper.OperatorPermissionMapper;
import com.hannstar.shiro.spring.boot.shiro.demo.service.IOperatorPermissionService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-04-02
 */
@Service
public class OperatorPermissionServiceImpl extends ServiceImpl<OperatorPermissionMapper, OperatorPermission> implements IOperatorPermissionService {

}
