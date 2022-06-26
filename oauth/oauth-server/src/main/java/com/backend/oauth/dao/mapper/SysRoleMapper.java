package com.backend.oauth.dao.mapper;

import com.backend.oauth.dao.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author mybatis-plus
 * @since 2022-06-07
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    SysRole findById(@Param("roleId") Long roleId);
}
