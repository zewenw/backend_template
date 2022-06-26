package com.backend.oauth.common.model;

import lombok.Data;

/**
 * JWT令牌扩充的信息实体类
 * 如需增加其他信息 在此处增加常量定义并在
 * com.backend.oauth.config.oauth.AuthorizationServerConfig#tokenEnhancer()
 * 方法中增加对应的value
 */
@Data
public class JwtUserConstant {
    /**
     * USER_ID
     */
    public static final String USER_ID = "USER_ID";

    /**
     * USER_NAME
     */
    public static final String USER_NAME = "USER_NAME";
}
