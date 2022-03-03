package com.lvzz.demo.controller;

import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.Result;
import com.lvzz.demo.entity.Trip;
import com.lvzz.demo.pojo.TripPoJo;
import com.lvzz.demo.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripService tripService;


    @PostMapping("/addTrip")
    public Result addTrip(@RequestBody Trip trip){
       int i = tripService.addTrip(trip);
       if(i>0){
           return Result.success();
       }
       else return Result.error();
    }

    @GetMapping("/queryHotTripList")
    public Result queryHotTripList(){
        List<TripPoJo> list = tripService.queryHotTripList();
        if(list == null){
            return Result.error();
        }
        return Result.success(list);
    }

    @GetMapping("/queryTripList")
    public Result queryTripList(@RequestParam Integer userId,@RequestParam(required = false,defaultValue = "4") Integer pageSize, @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<TripPoJo> list = tripService.querytripList(userId,pageNum,pageSize);
        if(list == null){
          return Result.error();
        }
        return Result.success(list);
    }


    @GetMapping("/queryAllTrip")
    public Result queryAllTrip(@RequestParam(required = false,defaultValue = "4") Integer pageSize, @RequestParam(required = false,defaultValue = "1") Integer pageNum,TripPoJo tripPoJo){
        Page<TripPoJo> list = tripService.queryAllTrip(pageNum,pageSize,tripPoJo);
        if(list == null){
            return Result.error();
        }
        return Result.success(list);
    }

    @GetMapping("/queryAllTripNoPage")
    public Result queryAllTripNoPage(TripPoJo tripPoJo){
        List<TripPoJo> list = tripService.queryAllTripNoPage(tripPoJo);
        if(list == null){
            return Result.error();
        }
        return Result.success(list);
    }

    @GetMapping("/queryTripListByLevelNoPage")
    public Result queryTripListByLevelNoPage(TripPoJo tripPoJo){
        List<TripPoJo> list = tripService.queryTripListByLevelNoPage(tripPoJo);
        if(list == null){
            return Result.error();
        }
        return Result.success(list);
    }

    @GetMapping("/queryUserTripsByKeyword")
    public Result queryUserTripsByKeyword(@RequestParam Integer userId,@RequestParam String keywords,@RequestParam(required = false,defaultValue = "4") Integer pageSize, @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<TripPoJo> tripPoJoList = tripService.queryUserTripsByKeyword(userId,keywords,pageNum,pageSize);
        if(tripPoJoList == null){
            return Result.error();
        }
        return Result.success(tripPoJoList);
    }

    @GetMapping("/queryTripsByKeyword")
    public Result queryTripsByKeyword(@RequestParam String keywords,@RequestParam(required = false,defaultValue = "4") Integer pageSize, @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<TripPoJo> tripPoJoList = tripService.queryTripsByKeyword(keywords,pageNum,pageSize);
        if(tripPoJoList == null){
            return Result.error();
        }
        return Result.success(tripPoJoList);
    }

    @GetMapping("/queryTripListByLevel")
    public Result queryTripListByLevel(@RequestParam(required = false,defaultValue = "4") Integer pageSize, @RequestParam(required = false,defaultValue = "1") Integer pageNum,TripPoJo tripPoJo){
        Page<TripPoJo> list = tripService.queryTripListByLevel(pageNum,pageSize,tripPoJo);
        if(list == null){
            return Result.error();
        }
        return Result.success(list);
    }

    @GetMapping("/queryUserTripListByLevel")
    public Result queryTripListByLevel(@RequestParam Integer userId,@RequestParam(required = false,defaultValue = "4") Integer pageSize, @RequestParam(required = false,defaultValue = "1") Integer pageNum,TripPoJo tripPoJo){
        Page<TripPoJo> list = tripService.queryUserTripListByLevel(userId,pageNum,pageSize,tripPoJo);
        if(list == null){
            return Result.error();
        }
        return Result.success(list);
    }

    @PostMapping("/queryTripById/{tripId}")
    public Result queryTripById(@PathVariable("tripId") Integer tripId){
        TripPoJo tripPoJo = tripService.queryTripById(tripId);
        if(tripPoJo!=null){
            return Result.success(tripPoJo);
        }
        else return Result.error();
    }

    @PostMapping("/updateTripById")
    public Result updateTripById(@RequestBody TripPoJo tripPoJo){
        int i = tripService.updateTripById(tripPoJo);
        if(i>0){
            return Result.success();
        }
        else return Result.error();
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
//        String extension = FilenameUtils.getExtension();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String prefix = UUID.randomUUID().toString().replaceAll("-", "");
        String newName=prefix+suffix;
        String path="H:/upload";

        file.transferTo(new File(path,newName));
        return Result.success("/pics/"+newName);
    }

    @PostMapping("/deleteTripById/{id}")
    public Result deleteTripById(@PathVariable("id") Integer id){
        int i = tripService.deleteTripById(id);
        if(i>0){
            return Result.success();
        }
        else return Result.error();
    }

}
