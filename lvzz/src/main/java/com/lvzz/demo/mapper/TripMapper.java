package com.lvzz.demo.mapper;

import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.Trip;
import com.lvzz.demo.pojo.TripPoJo;

import java.util.List;

public interface TripMapper {

    List<TripPoJo> querytripList();

    List<TripPoJo> queryTripListByLevel(TripPoJo tripPoJo);

    List<TripPoJo> queryAllTrip(TripPoJo tripPoJo);

    TripPoJo queryTripById(Integer tripId);

    int updateTripById(TripPoJo tripPoJo);

    int addTrip(Trip trip);
}
