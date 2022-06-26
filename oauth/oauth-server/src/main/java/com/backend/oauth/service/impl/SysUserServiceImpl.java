package com.backend.oauth.service.impl;

import com.backend.oauth.dao.entity.SysUser;
import com.backend.oauth.dao.mapper.SysUserMapper;
import com.backend.oauth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author mybatis-plus
 * @since 2022-06-08
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

}
