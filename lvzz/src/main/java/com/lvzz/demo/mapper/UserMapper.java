package com.lvzz.demo.mapper;

import com.lvzz.demo.entity.User;

public interface UserMapper {

       User queryUser(String userName,String password);

       User findByUsername(String userName);

       User queryUserById(Integer id);

       User findById(Integer id);

       int addUser(User user);

       int updateUserImg(String pic,Integer userId);

       int updateUserMsg(Integer id, String userName, String password, String phone);

       String queryPasswordById(Integer id);
}
