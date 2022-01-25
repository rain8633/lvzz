package com.lvzz.demo.mapper;

import com.lvzz.demo.entity.Page;
import com.lvzz.demo.pojo.TripPoJo;

import java.util.List;

public interface TripMapper {

    List<TripPoJo> querytripList();

    List<TripPoJo> queryTripListByLevel(TripPoJo tripPoJo);
}
