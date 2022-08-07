package com.blog.blog.services;

import com.blog.blog.payloads.CommentDTO;

public interface CommentService {
    
 public  CommentDTO createComment(CommentDTO commentDTO,Integer postId,Integer userId);
  public  void deleteComment(Integer commentId);
  public CommentDTO updateComment(CommentDTO commentDTO,Integer cId);
}
