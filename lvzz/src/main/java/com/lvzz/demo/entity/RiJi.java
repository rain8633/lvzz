package com.lvzz.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiJi {
    private Integer id;
    private Integer userId;
    private String content;
    private String pic;
    private String createTime;
    private String url;
}
