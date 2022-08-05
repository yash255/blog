package com.blog.blog.payloads;

import java.util.Date;

import com.blog.blog.models.Category;
import com.blog.blog.models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    

private String postTitle;
    
  
private String content;

private String imageName;

private Date addedDate;
    
 
private CategoryDTO category;
    
 
private UserDTO user;
}
