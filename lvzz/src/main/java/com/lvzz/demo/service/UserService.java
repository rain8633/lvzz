package com.lvzz.demo.service;

import com.lvzz.demo.entity.User;

public interface UserService {
    User queryUser(String userName, String password);

    int addUser(User user);

    User findByUsername(String userName);
}
