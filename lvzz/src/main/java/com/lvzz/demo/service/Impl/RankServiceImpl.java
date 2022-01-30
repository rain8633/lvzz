package com.lvzz.demo.service.Impl;

import com.lvzz.demo.entity.Grade;
import com.lvzz.demo.mapper.RankMapper;
import com.lvzz.demo.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    @Override
    public int addRank(Grade grade) {
        return rankMapper.addRank(grade);
    }

    @Override
    public int queryRankByTripId(Integer id) {
        if(rankMapper.queryUserNum(id)==0){
            return 0;
        }
        else return rankMapper.queryScoreNum(id) / rankMapper.queryUserNum(id);
    }
}
