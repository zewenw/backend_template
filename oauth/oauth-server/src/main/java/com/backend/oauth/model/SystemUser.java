package com.backend.oauth.model;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * 用户核心信息，用户其他相关信息可以另建对象，不在该对象上扩展
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser implements UserDetails {

    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号锁定状态 1-锁定 0-未锁定
     */
    private boolean locked;

    /**
     * 账号到期时间
     */
    private Date expiredTime;

    /**
     * 账号是否开启 1-开启 0-关闭
     */
    private boolean enabled;

    //权限+角色集合
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return DateUtil.compare(DateUtil.date(), this.expiredTime) >= 0;
    }

    // 账户是否未被锁
    @Override
    public boolean isAccountNonLocked() {
        return this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
