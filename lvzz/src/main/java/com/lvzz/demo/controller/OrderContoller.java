package com.lvzz.demo.controller;

import cn.hutool.json.JSONObject;
import com.lvzz.demo.alipay.AlipayWebPay;
import com.lvzz.demo.entity.Orders;
import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.Result;
import com.lvzz.demo.service.Impl.AliPayService;
import com.lvzz.demo.service.OrderService;
import com.lvzz.demo.utils.UUIDUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderContoller {

    @Autowired
    private OrderService orderService;


    private final AliPayService aliPayService;

    public OrderContoller(AliPayService aliPayService) {
        this.aliPayService = aliPayService;
    }

    @PostMapping("/addOrder")
    public Result addOrder(Orders orders) throws Exception {
         orders.setOid(UUIDUtils.getUUID());
        String date= DateFormatUtils.format(new Date(),"yyyy-MM-dd HH-mm-ss");
        orders.setCreateTime(date);
         int i = orderService.addOrder(orders);
         if(i>0){
//             String payResult = aliPayService.alipayFaceToFacePrecreate(orders.getTripName(),orders.getOid(),orders.getTotalPrice().toString());
//             JSONObject jsonObject = new JSONObject(payResult);
             String payResult = AlipayWebPay.getWebPayBody(orders);
//             JSONObject jsonObject = new JSONObject(payResult);
             //  响应前端
             return Result.success(payResult);
         }else return Result.error();
    }

    @PostMapping("/addOrder1")
    public Result addOrder1(Orders orders) throws Exception {
        orders.setOid(UUIDUtils.getUUID());
        String date= DateFormatUtils.format(new Date(),"yyyy-MM-dd HH-mm-ss");
        orders.setCreateTime(date);
        orders.setStatus(2);
        int i = orderService.addOrder(orders);
        if(i>0){
            return Result.success();
        }else return Result.error();
    }

    @RequestMapping("/findUserOrders")
    public Result findUserOrders(@RequestParam Integer userId,@RequestParam(required = false,defaultValue = "4") Integer pageSize, @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<Orders> ordersList = orderService.findUserOrders(userId,pageSize,pageNum);
        if(ordersList != null){
           return Result.success(ordersList);
        }
        else return Result.error();
    }

    /**
     *移动端查询订单
     * @param userId
     * @return
     */
    @RequestMapping("/findUserOrdersNoPage")
    public Result findUserOrdersNoPage(@RequestParam Integer userId){
       List<Orders> ordersList = orderService.findUserOrdersNoPage(userId);
        if(ordersList != null){
            return Result.success(ordersList);
        }
        else return Result.error();
    }

    @PostMapping("/updateOrderStatus")
    public Result updateOrderStatus(Orders orders) throws Exception {
        String payResult = AlipayWebPay.getWebPayBody(orders);
        return Result.success(payResult);
    }

    @PostMapping("/queryAllUserOrders")
    public Result queryAllUserOrders(@RequestParam(required = false,defaultValue = "4") Integer pageSize, @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<Orders> ordersList = orderService.queryAllUserOrders(pageSize,pageNum);
        if(ordersList != null){
            return Result.success(ordersList);
        }
        else return Result.error();
    }

    @GetMapping("/deleteorderById")
    public Result deleteorderById(@RequestParam String oid){
        int i = orderService.deleteorderById(oid);
        if(i>0){
            return Result.success();
        }
        else return Result.error();
    }
}
