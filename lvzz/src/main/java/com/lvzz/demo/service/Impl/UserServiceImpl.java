package com.lvzz.demo.service.Impl;

import com.lvzz.demo.entity.User;
import com.lvzz.demo.mapper.UserMapper;
import com.lvzz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUser(String userName, String password) {
        User user = userMapper.queryUser(userName,password);
        return user;
    }
}
