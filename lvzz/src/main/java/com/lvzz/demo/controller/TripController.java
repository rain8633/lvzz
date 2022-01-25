package com.lvzz.demo.controller;

import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.Result;
import com.lvzz.demo.pojo.TripPoJo;
import com.lvzz.demo.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripService tripService;


    @GetMapping("/queryHotTripList")
    public Result queryHotTripList(){
        List<TripPoJo> list = tripService.queryHotTripList();
        if(list == null){
            return Result.error();
        }
        return Result.success(list);
    }

    @GetMapping("/queryTripList")
    public Result queryTripList(@RequestParam(required = false,defaultValue = "4") Integer pageSize, @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<TripPoJo> list = tripService.querytripList(pageNum,pageSize);
        if(list == null){
          return Result.error();
        }
        return Result.success(list);
    }


    @GetMapping("/queryTripListByLevel")
    public Result queryTripListByLevel(@RequestParam(required = false,defaultValue = "4") Integer pageSize, @RequestParam(required = false,defaultValue = "1") Integer pageNum,TripPoJo tripPoJo){
        Page<TripPoJo> list = tripService.queryTripListByLevel(pageNum,pageSize,tripPoJo);
        if(list == null){
            return Result.error();
        }
        return Result.success(list);
    }
}
