package com.backend.oauth.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author mybatis-plus
 * @since 2022-06-08
 */
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别：1-男 2-女
     */
    private Boolean gender;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 用户状态：1-正常 0-禁用
     */
    private Boolean status;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 账号锁定状态 1-锁定 0-未锁定
     */
    private Boolean locked;

    /**
     * 账号到期时间
     */
    private Date expiredTime;

    /**
     * 账号是否开启 1-开启 0-关闭
     */
    private Boolean enabled;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
