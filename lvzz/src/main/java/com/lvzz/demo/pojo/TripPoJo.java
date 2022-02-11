package com.lvzz.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripPoJo {
    private Integer id;
    private String tripName;
    private String address;
    private Integer levelId;
    private String levelName;
    private String phone;
    private Integer price;
    private String picImg;
    private String info;
    private Integer min;
    private Integer max;
    private boolean shoucang;
}
