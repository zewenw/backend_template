package com.backend.oauth.dao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author mybatis-plus
 * @since 2022-06-07
 */
@Data
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 资源id
     */
    private Long permissionId;


}
