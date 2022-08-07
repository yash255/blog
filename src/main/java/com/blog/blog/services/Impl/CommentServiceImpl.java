package com.blog.blog.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.exception.ResourceNotFoundException;
import com.blog.blog.models.Comment;
import com.blog.blog.models.Post;
import com.blog.blog.models.User;
import com.blog.blog.payloads.CommentDTO;
import com.blog.blog.repositories.CommentRepo;
import com.blog.blog.repositories.PostRepo;
import com.blog.blog.repositories.UserRepo;
import com.blog.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
    
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId,Integer userId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
       User user  = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));

        Comment comment = this.modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
      Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "comment id", commentId));
      this.commentRepo.delete(com);
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO, Integer cId) {
    
        Comment com = this.commentRepo.findById(cId).orElseThrow(()->new ResourceNotFoundException("Comment", "comment id", cId));
        com.setContent(commentDTO.getContent());
        Comment updateComment = this.commentRepo.save(com); 
        
        return this.modelMapper.map(updateComment, CommentDTO.class);
    }
}
