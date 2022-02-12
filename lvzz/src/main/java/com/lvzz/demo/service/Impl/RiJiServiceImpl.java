package com.lvzz.demo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.RiJi;
import com.lvzz.demo.mapper.RiJiMapper;
import com.lvzz.demo.pojo.RiJiPoJo;
import com.lvzz.demo.pojo.TripPoJo;
import com.lvzz.demo.service.RiJiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiJiServiceImpl implements RiJiService {

    @Autowired
    private RiJiMapper riJiMapper;

    @Override
    public int AddRiJi(RiJi riJi) {
        return riJiMapper.AddRiJi(riJi);
    }

    @Override
    public Page<RiJi> queryAllRiJi(Integer userId, String content, String createTime, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<RiJi> rijiList=riJiMapper.queryRiJiList(userId,content,createTime);
        PageInfo<RiJi> pageInfo=new PageInfo<>(rijiList);

        return new Page<>(pageInfo.getPageNum(),pageInfo.getPageSize(),
                pageInfo.getList(),pageInfo.getPages(),pageInfo.getTotal());
    }

    @Override
    public RiJiPoJo queryRiJiDetail(Integer id) {
        return riJiMapper.queryRiJiDetail(id);
    }

    @Override
    public int updateRiJi(RiJi riJi) {
        return riJiMapper.updateRiJi(riJi);
    }

    @Override
    public int deleterijiById(Integer id, Integer userId) {
        return riJiMapper.deleterijiById(id,userId);
    }

    @Override
    public Page<RiJiPoJo> queryAllUserRiJi(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<RiJiPoJo> riJiPoJos=riJiMapper.queryAllUserRiJi();
        PageInfo<RiJiPoJo> pageInfo=new PageInfo<>(riJiPoJos);

        return new Page<>(pageInfo.getPageNum(),pageInfo.getPageSize(),
                pageInfo.getList(),pageInfo.getPages(),pageInfo.getTotal());
    }
}
