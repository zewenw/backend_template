package com.backend.oauth.dao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author mybatis-plus
 * @since 2022-06-07
 */
@Data
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;


}
