package com.lvzz.demo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lvzz.demo.entity.Page;
import com.lvzz.demo.mapper.TripMapper;
import com.lvzz.demo.pojo.TripPoJo;
import com.lvzz.demo.service.TripService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    public TripMapper tripMapper;

    @Override
    public Page<TripPoJo> querytripList(Integer pageNum, Integer pageSize) {
//        return tripMapper.querytripList();
        PageHelper.startPage(pageNum, pageSize);
        List<TripPoJo> TripList=tripMapper.querytripList();
        PageInfo<TripPoJo> pageInfo=new PageInfo<>(TripList);

        return new Page<>(pageInfo.getPageNum(),pageInfo.getPageSize(),
                pageInfo.getList(),pageInfo.getPages(),pageInfo.getTotal());
    }

    @Override
    public Page<TripPoJo> queryTripListByLevel(Integer pageNum, Integer pageSize,TripPoJo tripPoJo) {
        PageHelper.startPage(pageNum, pageSize);
        List<TripPoJo> TripList=tripMapper.queryTripListByLevel(tripPoJo);
        PageInfo<TripPoJo> pageInfo=new PageInfo<>(TripList);

        return new Page<>(pageInfo.getPageNum(),pageInfo.getPageSize(),
                pageInfo.getList(),pageInfo.getPages(),pageInfo.getTotal());
    }

    @Override
    public List<TripPoJo> queryHotTripList() {
        return tripMapper.querytripList();
    }
}
