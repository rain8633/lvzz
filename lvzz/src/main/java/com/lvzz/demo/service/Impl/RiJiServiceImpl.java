package com.lvzz.demo.service.Impl;

import com.lvzz.demo.entity.RiJi;
import com.lvzz.demo.mapper.RiJiMapper;
import com.lvzz.demo.service.RiJiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiJiServiceImpl implements RiJiService {

    @Autowired
    private RiJiMapper riJiMapper;

    @Override
    public int AddRiJi(RiJi riJi) {
        return riJiMapper.AddRiJi(riJi);
    }
}
