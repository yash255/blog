package com.blog.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blog.config.AppConstants;
import com.blog.blog.payloads.PostDTO;
import com.blog.blog.payloads.PostResponse;
import com.blog.blog.services.FileService;
import com.blog.blog.services.PostService;
import com.blog.blog.utils.ApiResponse;

@RestController
@RequestMapping("/api/post/")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;
    
    @Value("${project.image}")
    private String path;
    
    //create post
    @PostMapping("/create/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO,@PathVariable Integer userId,@PathVariable Integer categoryId){
    PostDTO createPost =    this.postService.createPost(postDTO, userId, categoryId);
    
    return new ResponseEntity<PostDTO>(createPost,HttpStatus.CREATED);

    }
    
    //get post by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId){
    List<PostDTO> posts =  this.postService.getPostByUser(userId);
    return new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
}

//get post by category
@GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId){
    List<PostDTO> posts =  this.postService.getPostByCategory(categoryId);
    return new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
}

//get all post
@GetMapping("/post/all")
public ResponseEntity<PostResponse> getAllPost(
    @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
@RequestParam (value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir
){
    
    PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
    return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
}

//get post by id
@GetMapping("/post/{postId}/posts")
public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
    PostDTO posts = this.postService.getPostById(postId);
    return ResponseEntity.ok(posts);
}

//delete post
@DeleteMapping("/post/{postId}")
public ApiResponse deletePost(@PathVariable Integer postId){
    this.postService.deletePost(postId);
    return new ApiResponse("Post is successfully deleted !!",true);
}

//update post
@PutMapping("/post/update/{postId}")
public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO,@PathVariable Integer postId){
    PostDTO updatePost  =this.postService.updatePost(postDTO, postId);
    return new ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);
}

//search post
@GetMapping("/post/search/{keywords}")
public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable("keywords") String keywords){
    List<PostDTO> result = this.postService.searchPosts(keywords);
    return new ResponseEntity<List<PostDTO>>(result,HttpStatus.OK);
}

//post image upload
@PostMapping("/post/image/upload/{postId}")
public ResponseEntity<PostDTO> uploadImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException{
    PostDTO postDTO =  this.postService.getPostById(postId);
    String fileName =    this.fileService.uploadImage(path, image);


postDTO.setImageName(fileName);
PostDTO updatePost = this.postService.updatePost(postDTO, postId);
return new  ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);


}

//serve image

@GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException{

    InputStream resource = this.fileService.getResource(path, imageName);
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    StreamUtils.copy(resource, response.getOutputStream());
}


}
