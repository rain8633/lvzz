package com.lvzz.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    private Integer id;
    private String tripName;
    private String address;
    private Integer levelId;
    private String phone;
    private Integer price;
    private String picImg;
    private String info;

}
