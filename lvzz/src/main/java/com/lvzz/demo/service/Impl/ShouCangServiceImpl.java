package com.lvzz.demo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.TongJi;
import com.lvzz.demo.mapper.ShouCangMapper;
import com.lvzz.demo.pojo.TripPoJo;
import com.lvzz.demo.service.ShouCangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShouCangServiceImpl implements ShouCangService {

    @Autowired
    public ShouCangMapper shouCangMapper;

    @Override
    public Page<TripPoJo> queryMyShouCang(Integer userId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TripPoJo> shouCangList=shouCangMapper.queryMyShouCang(userId);
        for(TripPoJo tripPoJo:shouCangList){
           tripPoJo.setShoucang(true);
        }
        PageInfo<TripPoJo> pageInfo=new PageInfo<>(shouCangList);

        return new Page<>(pageInfo.getPageNum(),pageInfo.getPageSize(),
                pageInfo.getList(),pageInfo.getPages(),pageInfo.getTotal());
    }

    @Override
    public int addShouCang(Integer userId, Integer id) {
        return shouCangMapper.addShouCang(userId, id);
    }

    @Override
    public int deleteShouCang(Integer userId, Integer tripId) {
        return shouCangMapper.deleteShouCang(userId, tripId);
    }

    @Override
    public List<TongJi> queryScInfo() {
        return shouCangMapper.queryScInfo();
    }

    @Override
    public List<TripPoJo> queryMyShouCangNoPage(Integer userId) {
        List<TripPoJo> shouCangList=shouCangMapper.queryMyShouCang(userId);
        for(TripPoJo tripPoJo:shouCangList){
            tripPoJo.setShoucang(true);
        }
        return shouCangList;
    }
}
