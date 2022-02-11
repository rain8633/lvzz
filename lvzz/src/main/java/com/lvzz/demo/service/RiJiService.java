package com.lvzz.demo.service;

import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.RiJi;
import com.lvzz.demo.pojo.RiJiPoJo;

import java.util.List;

public interface RiJiService {

    int AddRiJi(RiJi riJi);

    Page<RiJi> queryAllRiJi(Integer userId, String content, String createTime, Integer pageSize, Integer pageNum);

    RiJi queryRiJiDetail(Integer id);

    int updateRiJi(RiJi riJi);

    int deleterijiById(Integer id, Integer userId);

    Page<RiJiPoJo> queryAllUserRiJi(Integer pageSize,Integer pageNum);
}
