package com.lvzz.demo.mapper;

import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.RiJi;
import com.lvzz.demo.pojo.RiJiPoJo;
import com.lvzz.demo.pojo.TripPoJo;

import java.util.List;

public interface RiJiMapper {

    int  AddRiJi(RiJi riJi);

    List<RiJi> queryRiJiList(Integer userId, String content, String createTime);

    RiJi queryRiJiDetail(Integer id);

    int updateRiJi(RiJi riJi);

    int deleterijiById(Integer id, Integer userId);

    List<RiJiPoJo> queryAllUserRiJi();
}
