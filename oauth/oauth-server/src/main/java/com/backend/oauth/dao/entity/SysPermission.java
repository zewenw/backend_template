package com.backend.oauth.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author mybatis-plus
 * @since 2022-06-07
 */
@Data
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * URL权限标识
     */
    private String url;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
