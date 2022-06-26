package com.backend.oauth.common.model;

/**
 * JWT claims中的一些key常量
 */
public class JwtTokenConstant {
    /**
     * token key
     */
    public final static String TOKEN_NAME="Authorization";
    /**
     * token prefix
     */
    public final static String TOKEN_PREFIX="Bearer ";

    /**
     * JWT token 权限
     */
    public static final String AUTHORITIES_NAME="authorities";

    /**
     * JWT token 唯一编号
     */
    public static final String JTI="jti";

    /**
     * token 过期时间
     */
    public static final String EXP="exp";
    /**
     * redis key prefix 用于用户登出
     */
    public static final String REDIS_KEY_PREFIX="REDIS_KEY_PREFIX:";

}
