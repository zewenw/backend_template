package com.backend.oauth.dao.mapper;

import com.backend.oauth.dao.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 *
 * @author mybatis-plus
 * @since 2022-06-07
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    List<SysUserRole> findByUserId(@Param("userId") Integer userId);
}
