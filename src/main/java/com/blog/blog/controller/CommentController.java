package com.blog.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.payloads.CommentDTO;
import com.blog.blog.services.CommentService;
import com.blog.blog.utils.ApiResponse;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    
    @Autowired
    private CommentService commentService;

@PostMapping("/post/user/{userId}/create/{postId}/comment")
public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,@PathVariable Integer postId,@PathVariable Integer userId){
CommentDTO createComment = this.commentService.createComment(commentDTO,postId,userId);
return new ResponseEntity<CommentDTO>(createComment, HttpStatus.CREATED);
}

@DeleteMapping("/{commentId}")
public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
this.commentService.deleteComment(commentId);
return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully",true),HttpStatus.OK);
}

@PutMapping("/post/update/comment/{cId}")
public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO,@PathVariable Integer cId){
    CommentDTO updateComment = this.commentService.updateComment(commentDTO, cId);
    return new ResponseEntity<CommentDTO>(updateComment, HttpStatus.OK);
}


    
}
