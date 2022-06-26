package com.backend.oauth.dao.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author mybatis-plus
 * @since 2022-06-07
 */
@Data
@Builder
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色状态：0-正常；1-停用
     */
    private Boolean status;

    /**
     * 更新时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private Date updateTime;


}
