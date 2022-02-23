package com.lvzz.demo.service.Impl;

import com.lvzz.demo.entity.Admin;
import com.lvzz.demo.mapper.AdminMapper;
import com.lvzz.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    public AdminMapper adminMapper;

    @Override
    public Admin findByAdminName(String name) {
        return adminMapper.findByAdminName(name);
    }

    @Override
    public int addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin);
    }

    @Override
    public Admin queryAdmin(String name, String password) {
        return adminMapper.queryAdmin(name,password);
    }

    @Override
    public int updatePassword(String newPassword, Integer userId) {
        return adminMapper.updatePassword(newPassword,userId);
    }

    @Override
    public int updatePic(String pic, Integer userId) {
        return adminMapper.updatePic(pic, userId);
    }
}
