package com.lvzz.demo.mapper;

import com.lvzz.demo.entity.User;

public interface UserMapper {

       User queryUser(String userName,String password);
}
