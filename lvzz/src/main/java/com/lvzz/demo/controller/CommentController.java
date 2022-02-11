package com.lvzz.demo.controller;

import com.lvzz.demo.entity.Comment;
import com.lvzz.demo.entity.Result;
import com.lvzz.demo.pojo.CommentPoJo;
import com.lvzz.demo.service.CommentService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/getAllComment/{tripId}")
    public Result getAllComment(@PathVariable("tripId") Integer tripId){
        List<Comment> commentList=commentService.getAllComment(tripId);
        return Result.success(commentList);
    }

    @PostMapping("/addComment")
    public Result addComment(Comment comment){
        String date= DateFormatUtils.format(new Date(),"yyyy-MM-dd HH-mm-ss");
        comment.setCreateTime(date);
        int i=commentService.addComment(comment);
        if(i>0){
            return Result.success();
        }
        else return Result.error();

    }

    @PostMapping("/setTripLike")
    public Result setTripLike(@RequestParam Integer id, @RequestParam Integer up){
        int i = commentService.setTripLike(id,up);
        if(i>0){
            return Result.success();
        }
        else return Result.error();
    }
}
