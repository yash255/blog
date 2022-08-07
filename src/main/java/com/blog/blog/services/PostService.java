package com.blog.blog.services;

import java.util.List;

import com.blog.blog.payloads.PostDTO;
import com.blog.blog.payloads.PostResponse;

public interface PostService {
    
//create
PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);

//update
PostDTO updatePost(PostDTO postDTO,Integer postId);

//delete
void deletePost(Integer postId);

//get all
PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

//get single post
PostDTO getPostById(Integer postId);

//get all post by category
List<PostDTO> getPostByCategory(Integer categoryId);

//get all post by user
List<PostDTO> getPostByUser(Integer userId);

//search posts
List<PostDTO> searchPosts(String keyword);
}
