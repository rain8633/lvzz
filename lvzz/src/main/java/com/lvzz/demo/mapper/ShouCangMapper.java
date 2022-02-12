package com.lvzz.demo.mapper;

import com.lvzz.demo.entity.TongJi;
import com.lvzz.demo.pojo.TripPoJo;

import java.util.List;

public interface ShouCangMapper {

    List<TripPoJo> queryMyShouCang(Integer userId);

    List<Integer> isShoucang(Integer userId);

    int addShouCang(Integer userId, Integer id);

    int deleteShouCang(Integer userId,Integer tripId);

    List<TongJi> queryScInfo();
}
