package com.backend.oauth.dao.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author mybatis-plus
 * @since 2022-06-07
 */
public class OauthClientDetailsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 资源的id，多个用逗号分隔
     */
    private String resourceIds;

    /**
     * 客户端的秘钥
     */
    private String clientSecret;

    /**
     * 客户端的权限，多个用逗号分隔
     */
    private String scope;

    /**
     * 授权类型，五种，多个用逗号分隔
     */
    private String authorizedGrantTypes;

    /**
     * 授权码模式的跳转uri
     */
    private String webServerRedirectUri;

    /**
     * 权限，多个用逗号分隔
     */
    private String authorities;

    /**
     * access_token的过期时间，单位毫秒，覆盖掉硬编码
     */
    private Integer accessTokenValidity;

    /**
     * refresh_token的过期时间，单位毫秒，覆盖掉硬编码
     */
    private Integer refreshTokenValidity;

    /**
     * 扩展字段，JSON
     */
    private String additionalInformation;

    /**
     * 默认false，是否自动授权
     */
    private String autoapprove;


}
