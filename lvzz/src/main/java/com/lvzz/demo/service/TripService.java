package com.lvzz.demo.service;

import com.lvzz.demo.entity.Page;
import com.lvzz.demo.pojo.TripPoJo;

import java.util.List;

public interface TripService {

    public Page<TripPoJo> querytripList(Integer pageNum, Integer pageSize);

    Page<TripPoJo> queryTripListByLevel(Integer pageNum, Integer pageSize,TripPoJo tripPoJo);

    List<TripPoJo> queryHotTripList();
}
