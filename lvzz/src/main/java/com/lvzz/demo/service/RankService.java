package com.lvzz.demo.service;

import com.lvzz.demo.entity.Grade;

public interface RankService {

    int addRank(Grade grade);

    int queryRankByTripId(Integer id);
}
