package com.lvzz.demo.controller;


import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.Result;
import com.lvzz.demo.entity.TongJi;
import com.lvzz.demo.pojo.TripPoJo;
import com.lvzz.demo.service.ShouCangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shouCang")
public class ShouCangController {

    @Autowired
    public ShouCangService shouCangService;

    @GetMapping("/queryMyShouCang")
    public Result queryMyShouCang(@RequestParam Integer userId, @RequestParam(required = false,defaultValue = "4") Integer pageSize, @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<TripPoJo> list = shouCangService.queryMyShouCang(userId, pageNum, pageSize);
        if(list!=null){
            return Result.success(list);
        }
        else return Result.error();
    }


    @PostMapping("/queryMyShouCangNoPage")
    public Result queryMyShouCangNoPage(@RequestParam Integer userId){
        List<TripPoJo> list = shouCangService.queryMyShouCangNoPage(userId);
        if(list!=null){
            return Result.success(list);
        }
        else return Result.error();
    }

    @PostMapping("/addShouCang/{userId}/{id}")
    public Result addShouCang(@PathVariable("userId") Integer userId,@PathVariable("id") Integer id){
        int i = shouCangService.addShouCang(userId,id);
        if(i>0){
            return Result.success();
        }
        else return Result.error();
    }

    @DeleteMapping("/deleteShouCang")
    public Result deleteShouCang(@RequestParam Integer userId,@RequestParam Integer tripId){
        int i = shouCangService.deleteShouCang(userId, tripId);
        if(i>0){
            return Result.success();
        }
        else return Result.error();
    }

    @PostMapping("/tongji")
    public Result tongji(){
        List<TongJi> tongJiList=shouCangService.queryScInfo();
        if(tongJiList!=null){
            return Result.success(tongJiList);
        }
        return Result.error();
    }
}
