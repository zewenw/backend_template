package com.backend.oauth.dao.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author mybatis-plus
 * @since 2022-06-07
 */
public class SysPermissionDto implements Serializable {

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
