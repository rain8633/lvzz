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

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUserImg(String pic, Integer userId) {
        return userMapper.updateUserImg(pic,userId);
    }

    @Override
    public User queryUserById(Integer id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public User findByUsername(String userName) {
        return userMapper.findByUsername(userName);
    }

    @Override
    public int updateUserMsg(Integer id, String userName, String password, String phone) {
        return userMapper.updateUserMsg(id,userName,password,phone);
    }

    @Override
    public String queryPasswordById(Integer id) {
        return userMapper.queryPasswordById(id);
    }
}
