package com.backend.oauth.service.oauth;

import cn.hutool.core.util.ArrayUtil;
import com.backend.oauth.common.model.SysConstant;
import com.backend.oauth.model.SystemUser;
import com.backend.oauth.dao.entity.SysRole;
import com.backend.oauth.dao.entity.SysUser;
import com.backend.oauth.dao.entity.SysUserRole;
import com.backend.oauth.dao.mapper.SysRoleMapper;
import com.backend.oauth.dao.mapper.SysUserMapper;
import com.backend.oauth.dao.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Jwt 用户详情查询
 */
@Service
public class JwtTokenUserDetailService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户
        SysUser sysUser = sysUserMapper.findUserByUsername(username);
        if (Objects.isNull(sysUser))
            throw new UsernameNotFoundException("用户不存在！");
        //查询角色
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.findByUserId(sysUser.getId());
        //查询权限
        List<String> roles = new ArrayList<>();
        for (SysUserRole userRole : sysUserRoles) {
            SysRole sysRole = sysRoleMapper.findById(userRole.getRoleId());
            roles.add(SysConstant.ROLE_PREFIX + sysRole.getCode());
        }
        return SystemUser.builder()
                .userId(sysUser.getUserId())
                .username(sysUser.getUsername())
                .password(sysUser.getPassword())
                .locked(sysUser.getLocked())
                .enabled(sysUser.getEnabled())
                .expiredTime(sysUser.getExpiredTime())
                .enabled(sysUser.getEnabled())
                //将角色放入authorities中
                .authorities(AuthorityUtils.createAuthorityList(ArrayUtil.toArray(roles,String.class)))
                .build();
    }
}
