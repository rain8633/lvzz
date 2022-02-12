package com.lvzz.demo.service;


import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.TongJi;
import com.lvzz.demo.pojo.TripPoJo;

import java.util.List;

public interface ShouCangService {

    Page<TripPoJo> queryMyShouCang(Integer userId,Integer pageNum,Integer pageSize);

    int addShouCang(Integer userId, Integer id);

    int deleteShouCang(Integer userId,Integer tripId);

    List<TongJi> queryScInfo();
}
