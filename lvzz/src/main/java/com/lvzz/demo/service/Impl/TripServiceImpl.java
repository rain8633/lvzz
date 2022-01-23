package com.lvzz.demo.service.Impl;

import com.lvzz.demo.mapper.TripMapper;
import com.lvzz.demo.pojo.TripPoJo;
import com.lvzz.demo.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    public TripMapper tripMapper;

    @Override
    public List<TripPoJo> querytripList() {
        return tripMapper.querytripList();
    }
}
