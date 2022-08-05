package com.blog.blog.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.models.Post;
import com.blog.blog.payloads.PostDTO;
import com.blog.blog.services.PostService;

@RestController
@RequestMapping("/api/post/")
public class PostController {
    @Autowired
    private PostService postService;
    
    //create
    @PostMapping("/create/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,@PathVariable Integer userId,@PathVariable Integer categoryId){
    PostDTO createPost =    this.postService.createPost(postDTO, userId, categoryId);
    
    return new ResponseEntity<PostDTO>(createPost,HttpStatus.CREATED);

    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId){
    List<PostDTO> posts =  this.postService.getPostByUser(userId);
    return new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
}
@GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId){
    List<PostDTO> posts =  this.postService.getPostByCategory(categoryId);
    return new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
}
@GetMapping("/")
public ResponseEntity<List<PostDTO>> getAllPost(){
    List<PostDTO> posts = this.postService.getAllPost();
    return ResponseEntity.ok(posts);
}

@GetMapping("/post/{postId}/posts")
public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
    PostDTO posts = this.postService.getPostById(postId);
    return ResponseEntity.ok(posts);
}
}
