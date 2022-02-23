package com.lvzz.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    private String oid;
    private String userName;
    private String phone;
    private String tripName;
    private String address;
    private Integer totalPrice;
    private String createTime;
    private Integer status;
}
