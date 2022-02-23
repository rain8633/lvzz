package com.lvzz.demo.service.Impl;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AliPayService {

    public String alipayFaceToFacePrecreate(String subject,String outTradeNo,String totalPrice){
        try{
           //发起api调用
            AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace()
                    .preCreate(subject, outTradeNo, totalPrice);
            //处理异常
            if(ResponseChecker.success(response)){
               log.info("当面付调用成功");
                return response.httpBody;
            }else{
                log.info("调用失败 原因："+response.msg+" ,"+response.subMsg);

            }
        }catch (Exception e){
            log.info("调用异常",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
