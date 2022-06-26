package com.backend.oauth.dao.mapper;

import com.backend.oauth.dao.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author mybatis-plus
 * @since 2022-06-08
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser findUserByUsername(@Param("username") String username);
}
