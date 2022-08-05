package com.blog.blog.services;

import java.util.List;

import com.blog.blog.models.Post;
import com.blog.blog.payloads.PostDTO;

public interface PostService {
    
//create
PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);

//update
Post updatePost(PostDTO postDTO,Integer postId);

//delete
void deletePost(Integer postId);

//get all
List<PostDTO> getAllPost();

//get single post
PostDTO getPostById(Integer postId);

//get all post by category
List<PostDTO> getPostByCategory(Integer categoryId);

//get all post by user
List<PostDTO> getPostByUser(Integer userId);

//search posts
List<Post> searchPosts(String keyword);
}
