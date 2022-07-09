package com.backend.service.dao.mapper;

import com.backend.service.dao.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  User Mapper 接口
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> getUser();
}
