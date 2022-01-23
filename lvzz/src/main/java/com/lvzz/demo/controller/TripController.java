package com.lvzz.demo.controller;

import com.lvzz.demo.entity.Result;
import com.lvzz.demo.pojo.TripPoJo;
import com.lvzz.demo.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping("/queryTripList")
    public Result queryTripList(){
        List<TripPoJo> list = tripService.querytripList();
        if(list == null){
          return Result.error();
        }
        return Result.success(list);
    }
}
