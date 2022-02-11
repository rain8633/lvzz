package com.lvzz.demo.mapper;

import com.lvzz.demo.entity.Comment;
import com.lvzz.demo.pojo.CommentPoJo;

import java.util.List;

public interface CommentMapper {
    List<Comment> getAllComment(Integer tripId);

    int addComment(Comment comment);

    int setTripLike(Integer id, Integer up);
}
