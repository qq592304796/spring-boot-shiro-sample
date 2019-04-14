package com.hannstar.shiro.spring.boot.shiro.demo.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.OperatorUserRole;
import com.hannstar.shiro.spring.boot.shiro.demo.entity.extend.UserRoleDOView;

/**
 * <p>
 * 用户角色关系表 Mapper 接口
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-04-02
 */
public interface OperatorUserRoleMapper extends BaseMapper<OperatorUserRole> {
    
    /**
     * @author zhangyong
     * @date 2017/4/19.
     */
     List<UserRoleDOView> findUserRoleByUserId(Integer userId);

}
