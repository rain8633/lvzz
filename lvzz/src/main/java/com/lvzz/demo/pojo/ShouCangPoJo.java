package com.lvzz.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShouCangPoJo {
    private Integer id;
    private Integer userId;
    private Integer tripId;
    private String tripName;
    private String address;
}
