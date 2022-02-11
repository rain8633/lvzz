package com.lvzz.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiJi {
    private Integer id;
    private Integer userId;
    private String  title;
    private Integer feelScore;
    private String content;
    private String pic;

//    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date createTime;

    private String createTime;
    private String url;
}
