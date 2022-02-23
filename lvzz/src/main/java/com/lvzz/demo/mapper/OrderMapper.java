package com.lvzz.demo.mapper;

import com.lvzz.demo.entity.Orders;

import java.util.List;

public interface OrderMapper {
    int addOrder(Orders orders);

    int updateOrdersStateById(Integer status,String oid);

    List<Orders> findUserOrders(Integer userId);

    List<Orders> queryAllUserOrders();

    int deleteorderById(String oid);
}
