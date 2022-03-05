package com.lvzz.demo.service;

import com.lvzz.demo.entity.Orders;
import com.lvzz.demo.entity.Page;

import java.util.List;

public interface OrderService {

    int addOrder(Orders orders);

    boolean updateOrdersStateById(Integer status,String oid);

    Page<Orders> findUserOrders(Integer userId,Integer pageSize,Integer pageNum);

    Page<Orders> queryAllUserOrders(Integer pageSize, Integer pageNum);

    int deleteorderById(String oid);

    List<Orders> findUserOrdersNoPage(Integer userId);
}
