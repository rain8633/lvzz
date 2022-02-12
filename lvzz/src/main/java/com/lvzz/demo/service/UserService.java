package com.lvzz.demo.service;

import com.lvzz.demo.entity.User;

import java.util.List;

public interface UserService {
    User queryUser(String userName, String password);

    int addUser(User user);

    int updateUserImg(String pic,Integer userId);

    User queryUserById(Integer id);

    User findByUsername(String userName);

    int updateUserMsg(Integer id, String userName, String password, String phone);

    String queryPasswordById(Integer id);

    List<User> queryAllUser();

    int deleteUserById(Integer id);
}
