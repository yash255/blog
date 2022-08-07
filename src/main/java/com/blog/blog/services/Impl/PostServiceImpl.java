package com.blog.blog.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blog.exception.ResourceNotFoundException;
import com.blog.blog.models.Category;
import com.blog.blog.models.Post;
import com.blog.blog.models.User;
import com.blog.blog.payloads.PostDTO;
import com.blog.blog.payloads.PostResponse;
import com.blog.blog.repositories.CategoryRepo;
import com.blog.blog.repositories.PostRepo;
import com.blog.blog.repositories.UserRepo;
import com.blog.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{
    
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId) {
        User user  = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userid", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryid", categoryId));
       
        Post post = this.modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

      Post newPost =   this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post =  this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
        post.setPostTitle(postDTO.getPostTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());

      Post updatePost =  this.postRepo.save(post);
        return this.modelMapper.map(updatePost, PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {
    Post post =  this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
    this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
        
        Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        // if (sortDir.equalsIgnoreCase("asc")) {
        //     sort=Sort.by(sortBy).ascending();
        // } else {
        //     sort = Sort.by(sortBy).descending();    
        // }
        
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<Post> pagePost = this.postRepo.findAll(pageable);
        List<Post> allPost = pagePost.getContent();
        
        // List<Post> posts = this.postRepo.findAll();
        List<PostDTO> postdDtos = allPost.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postdDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        
        return postResponse;
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
        
        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
        
      
        
        List<Post> posts = this.postRepo.findByCategory(cat);
        List<PostDTO> postDTOs = posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postDTOs;
    }

    @Override
    public List<PostDTO> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDTO> postDTOs = posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOs;
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
    List<Post> posts = this.postRepo.findBypostTitleContaining(keyword);
    List<PostDTO> postDTOs = posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
   
    return postDTOs;
    }
    
}
