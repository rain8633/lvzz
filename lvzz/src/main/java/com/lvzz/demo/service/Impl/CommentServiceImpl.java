package com.lvzz.demo.service.Impl;

import com.lvzz.demo.entity.Comment;
import com.lvzz.demo.mapper.CommentMapper;
import com.lvzz.demo.pojo.CommentPoJo;
import com.lvzz.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getAllComment(Integer tripId) {
        return commentMapper.getAllComment(tripId);
    }

    @Override
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    @Override
    public int setTripLike(Integer id, Integer up) {
        return commentMapper.setTripLike(id,up);
    }
}
