package com.lvzz.demo.service;

import com.lvzz.demo.entity.Admin;

public interface AdminService {

    Admin findByAdminName(String name);

    int addAdmin(Admin admin);

    Admin queryAdmin(String name, String password);
}
