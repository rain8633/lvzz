package com.lvzz.demo.pojo;

import com.lvzz.demo.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPoJo extends Comment {
    private String userName;
}
