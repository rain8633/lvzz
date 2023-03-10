package com.lvzz.demo.mapper;

import com.lvzz.demo.entity.Admin;

public interface AdminMapper {

    Admin findByAdminName(String name);

    int addAdmin(Admin admin);

    Admin queryAdmin(String name, String password);

    int updatePassword(String newPassword, Integer userId);

    int updatePic(String pic, Integer userId);
}
