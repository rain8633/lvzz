package com.lvzz.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    private Integer id;
    private Integer userId;
    private Integer tripId;
    private Integer score;

}
