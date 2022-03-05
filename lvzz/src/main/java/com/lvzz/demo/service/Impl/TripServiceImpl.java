package com.lvzz.demo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.Trip;
import com.lvzz.demo.mapper.ShouCangMapper;
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

    @Autowired
    public ShouCangMapper shouCangMapper;

    @Override
    public Page<TripPoJo> querytripList(Integer userId,Integer pageNum, Integer pageSize) {
//        return tripMapper.querytripList();
        PageHelper.startPage(pageNum, pageSize);
        List<TripPoJo> TripList=tripMapper.querytripList();
            List<Integer> list = shouCangMapper.isShoucang(userId);
            for (Integer s : list) {
                for (TripPoJo tripPoJo : TripList) {
                    if (tripPoJo.getId() == s) {
                        tripPoJo.setShoucang(true);
                    }
                }
            }

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

    @Override
    public Page<TripPoJo> queryAllTrip(Integer pageNum, Integer pageSize,TripPoJo tripPoJo1) {
        PageHelper.startPage(pageNum, pageSize);
        List<TripPoJo> TripList=tripMapper.queryAllTrip(tripPoJo1);
        PageInfo<TripPoJo> pageInfo=new PageInfo<>(TripList);

        return new Page<>(pageInfo.getPageNum(),pageInfo.getPageSize(),
                pageInfo.getList(),pageInfo.getPages(),pageInfo.getTotal());
    }

    @Override
    public Page<TripPoJo> queryUserTripListByLevel(Integer userId, Integer pageNum, Integer pageSize, TripPoJo tripPoJo) {
        PageHelper.startPage(pageNum, pageSize);
        List<TripPoJo> TripList=tripMapper.queryTripListByLevel(tripPoJo);
        List<Integer> list = shouCangMapper.isShoucang(userId);
        for (Integer s : list) {
            for (TripPoJo tripPoJo1 : TripList) {
                if (tripPoJo1.getId() == s) {
                    tripPoJo1.setShoucang(true);
                }
            }
        }
        PageInfo<TripPoJo> pageInfo=new PageInfo<>(TripList);

        return new Page<>(pageInfo.getPageNum(),pageInfo.getPageSize(),
                pageInfo.getList(),pageInfo.getPages(),pageInfo.getTotal());
    }

    @Override
    public TripPoJo queryTripById(Integer tripId) {
        return tripMapper.queryTripById(tripId);
    }

    @Override
    public int updateTripById(TripPoJo tripPoJo) {
        return tripMapper.updateTripById(tripPoJo);
    }

    @Override
    public int addTrip(Trip trip) {
        return tripMapper.addTrip(trip);
    }

    @Override
    public int deleteTripById(Integer id) {
        return tripMapper.deleteTripById(id);
    }

    @Override
    public Page<TripPoJo> queryUserTripsByKeyword(Integer userId,String keywords,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TripPoJo> TripList=tripMapper.queryTripsByKeyword(keywords);
        List<Integer> list = shouCangMapper.isShoucang(userId);
            for (Integer s : list) {
                for (TripPoJo tripPoJo1 : TripList) {
                    if (tripPoJo1.getId() == s) {
                        tripPoJo1.setShoucang(true);
                    }
                }
            }
        PageInfo<TripPoJo> pageInfo=new PageInfo<>(TripList);

        return new Page<>(pageInfo.getPageNum(),pageInfo.getPageSize(),
                pageInfo.getList(),pageInfo.getPages(),pageInfo.getTotal());

    }

    @Override
    public Page<TripPoJo> queryTripsByKeyword(String keywords, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TripPoJo> TripList=tripMapper.queryTripsByKeyword(keywords);
        PageInfo<TripPoJo> pageInfo=new PageInfo<>(TripList);

        return new Page<>(pageInfo.getPageNum(),pageInfo.getPageSize(),
                pageInfo.getList(),pageInfo.getPages(),pageInfo.getTotal());
    }

    @Override
    public List<TripPoJo> queryAllTripNoPage(TripPoJo tripPoJo) {
        return tripMapper.queryAllTrip(tripPoJo);
    }

    @Override
    public List<TripPoJo> queryTripListByLevelNoPage(TripPoJo tripPoJo) {
        return tripMapper.queryTripListByLevel(tripPoJo);
    }

    /**
     * 移动端查询旅游景点同时判断是否收藏
     * @param userId
     * @return
     */
    @Override
    public List<TripPoJo> queryUserAllTripNoPage(Integer userId) {
        List<TripPoJo> TripList=tripMapper.querytripList();
        List<Integer> list = shouCangMapper.isShoucang(userId);
        for (Integer s : list) {
            for (TripPoJo tripPoJo1 : TripList) {
                if (tripPoJo1.getId() == s) {
                    tripPoJo1.setShoucang(true);
                }
            }
        }
        return TripList;
    }

    /**
     * 通过类别查看景点是否被用户收藏
     * @param tripPoJo
     * @param userId
     * @return
     */
    @Override
    public List<TripPoJo> queryUserTripListByLevelNoPage(TripPoJo tripPoJo, Integer userId) {
        List<TripPoJo> TripList=tripMapper.queryTripListByLevel(tripPoJo);
        List<Integer> list = shouCangMapper.isShoucang(userId);
        for (Integer s : list) {
            for (TripPoJo tripPoJo1 : TripList) {
                if (tripPoJo1.getId() == s) {
                    tripPoJo1.setShoucang(true);
                }
            }
        }
       return TripList;
    }

    @Override
    public List<TripPoJo> queryUserHotTripList(Integer userId) {
        List<TripPoJo> TripList=tripMapper.querytripList();
        List<Integer> list = shouCangMapper.isShoucang(userId);
        for (Integer s : list) {
            for (TripPoJo tripPoJo1 : TripList) {
                if (tripPoJo1.getId() == s) {
                    tripPoJo1.setShoucang(true);
                }
            }
        }
        return TripList;
    }


}
