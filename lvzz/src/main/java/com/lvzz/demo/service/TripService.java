package com.lvzz.demo.service;

import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.Trip;
import com.lvzz.demo.pojo.TripPoJo;

import java.util.List;

public interface TripService {

    //用户登录时查所有景点，看景点有没有被用户收藏
    public Page<TripPoJo> querytripList(Integer userId,Integer pageNum, Integer pageSize);

    Page<TripPoJo> queryTripListByLevel(Integer pageNum, Integer pageSize,TripPoJo tripPoJo);

    List<TripPoJo> queryHotTripList();

     //用户未登录时或管理员查询时调用
    Page<TripPoJo> queryAllTrip(Integer pageNum, Integer pageSize,TripPoJo tripPoJo);

    //用户登陆时调用
    Page<TripPoJo> queryUserTripListByLevel(Integer userId, Integer pageNum, Integer pageSize, TripPoJo tripPoJo);

    TripPoJo queryTripById(Integer tripId);

    int updateTripById(TripPoJo tripPoJo);

    int addTrip(Trip trip);

    int deleteTripById(Integer id);

    Page<TripPoJo> queryUserTripsByKeyword(Integer userId,String keywords,Integer pageNum,Integer pageSize);

    Page<TripPoJo> queryTripsByKeyword(String keywords, Integer pageNum, Integer pageSize);
}
