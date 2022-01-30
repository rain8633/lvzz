package com.lvzz.demo.mapper;

import com.lvzz.demo.entity.Grade;

public interface RankMapper {
    int addRank(Grade grade);

    int queryScoreNum(Integer tripId);

    int queryUserNum(Integer tripId);
}
