package com.lvzz.demo.service;

import com.lvzz.demo.entity.Comment;
import com.lvzz.demo.pojo.CommentPoJo;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComment(Integer tripId);

    int addComment(Comment comment);
}
