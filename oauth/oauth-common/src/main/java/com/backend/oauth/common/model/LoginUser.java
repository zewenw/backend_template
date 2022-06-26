package com.backend.oauth.common.model;

import lombok.Data;

import java.util.Date;

/**
 * 保存登录用户的信息，此处可以根据业务需要扩展
 */
@Data
public class LoginUser extends JwtUserConstant {

    public final static String LOGIN_USER_ATTRIBUTE="loginUser_attribute";

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * jwt token唯一编号
     */
    private String jti;

    /**
     * token过期时间
     */
    private Date expireDate;

    /**
     * 用户权限
     */
    private String[] authorities;

}
