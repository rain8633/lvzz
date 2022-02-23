package com.lvzz.demo.alipay;

import com.lvzz.demo.config.WebSocket;
import com.lvzz.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：
 * @date ：
 * @description ：支付宝支付异步通知
 * @version: 1.0
 */
@Controller
public class AlipayWebNotify {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WebSocket webSocket;

    @RequestMapping("/alipay/web/notify")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(AlipayWebPay.signVerified(request.getParameterMap())) {//验证成功
            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");
            // 发送给支付宝
            response.getWriter().println("success");
            webSocket.sendMessage("true");
            System.out.println("notify_success");

            //订单状态,修改为已经付款
            if(orderService.updateOrdersStateById(1,out_trade_no)){
                System.out.println("状态更新成功！");
            }else{
                System.out.println("状态更新失败！");
            }
        }else {//验证失败
            response.getWriter().println("fail");
            System.out.println("notify_fail");
        }


    }
}
