package com.backend.oauth.dao.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author mybatis-plus
 * @since 2022-06-07
 */
public class SysRolePermissionDto implements Serializable {

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
