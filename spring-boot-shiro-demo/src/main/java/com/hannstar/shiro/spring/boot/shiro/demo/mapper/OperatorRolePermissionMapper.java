package com.hannstar.shiro.spring.boot.shiro.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.OperatorRolePermission;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.extend.RolePermissionDOView;

/**
 * <p>
 * 角色权限关系表 Mapper 接口
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-04-02
 */
public interface OperatorRolePermissionMapper extends BaseMapper<OperatorRolePermission> {
    
    List<RolePermissionDOView>  findRolePermission(Integer roleId);

    List<RolePermissionDOView>  findRolePermissionList(@Param("roleids") List<Integer> roleids);
    
}
