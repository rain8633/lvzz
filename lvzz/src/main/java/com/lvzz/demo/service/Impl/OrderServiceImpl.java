package com.lvzz.demo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lvzz.demo.entity.Orders;
import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.RiJi;
import com.lvzz.demo.mapper.OrderMapper;
import com.lvzz.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int addOrder(Orders orders) {
        return orderMapper.addOrder(orders);
    }

    @Override
    public boolean updateOrdersStateById(Integer status,String oid) {
        int i=orderMapper.updateOrdersStateById(status,oid);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public Page<Orders> findUserOrders(Integer userId, Integer pageSize, Integer pageNum) {

        PageHelper.startPage(pageNum, pageSize);
        List<Orders> rijiList=orderMapper.findUserOrders(userId);
        PageInfo<Orders> pageInfo=new PageInfo<>(rijiList);

        return new Page<>(pageInfo.getPageNum(),pageInfo.getPageSize(),
                pageInfo.getList(),pageInfo.getPages(),pageInfo.getTotal());
    }

    @Override
    public Page<Orders> queryAllUserOrders(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> rijiList=orderMapper.queryAllUserOrders();
        PageInfo<Orders> pageInfo=new PageInfo<>(rijiList);

        return new Page<>(pageInfo.getPageNum(),pageInfo.getPageSize(),
                pageInfo.getList(),pageInfo.getPages(),pageInfo.getTotal());
    }

    @Override
    public int deleteorderById(String oid) {
        return orderMapper.deleteorderById(oid);
    }

    @Override
    public List<Orders> findUserOrdersNoPage(Integer userId) {

        List<Orders> rijiList=orderMapper.findUserOrders(userId);


        return rijiList;
    }
}
