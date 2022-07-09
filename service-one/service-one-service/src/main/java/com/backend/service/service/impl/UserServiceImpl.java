package com.backend.service.service.impl;

import cn.hutool.json.JSONUtil;
import com.backend.service.dao.mapper.UserMapper;
import com.backend.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getUser() {
        return JSONUtil.toJsonStr(userMapper.getUser());
    }
}
