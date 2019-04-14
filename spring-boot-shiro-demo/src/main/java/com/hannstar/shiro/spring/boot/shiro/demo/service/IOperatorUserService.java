package com.hannstar.shiro.spring.boot.shiro.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.OperatorUser;
import com.hannstar.shiro.spring.boot.shiro.demo.model.PerssionInfoListBO;
import com.hannstar.shiro.spring.boot.shiro.demo.model.RoleBO;
import com.hannstar.shiro.spring.boot.shiro.demo.model.UserBO;

/**
 * <p>
 * 管理员信息 服务类
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-04-02
 */
public interface IOperatorUserService extends IService<OperatorUser> {
    
    UserBO find(String account, String pwd);

    List<RoleBO> findUserRoleByUserId(Integer userId);

    List<String> findRolePermission(Integer roleId);

    UserBO find(String account);

    List<PerssionInfoListBO> findRolePermissionList(Integer id);
    
}
