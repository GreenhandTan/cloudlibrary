package com.itheima.service.impl;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    // 注入UserMapper对象
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public Integer addUser(String user_name, String user_password, String user_email, String user_role) {
        User user = new User(user_name, user_password, user_email, user_role);
        return userMapper.addUser(user);
    }
}
