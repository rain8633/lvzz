package com.lvzz.demo.service;


import com.lvzz.demo.entity.Page;
import com.lvzz.demo.pojo.TripPoJo;

public interface ShouCangService {

    Page<TripPoJo> queryMyShouCang(Integer userId,Integer pageNum,Integer pageSize);

    int addShouCang(Integer userId, Integer id);

    int deleteShouCang(Integer userId,Integer tripId);
}
