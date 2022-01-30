package com.lvzz.demo.controller;

import com.lvzz.demo.entity.Grade;
import com.lvzz.demo.entity.Result;
import com.lvzz.demo.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rank")
public class RankController {

    @Autowired
    private RankService rankService;

    @GetMapping("/addRank")
    public Result addRank(Grade grade){
        int i=rankService.addRank(grade);
        if(i>0){
            return Result.success();
        }
        return Result.error();
    }

    @PostMapping("/queryRankByTripId/{id}")
    public Result queryRankByTripId(@PathVariable("id") Integer id){
         int rank=rankService.queryRankByTripId(id);
         return Result.success(rank);
    }

}
