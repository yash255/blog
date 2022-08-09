package com.blog.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



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

private Set<CommentDTO> comments = new HashSet<>();
}
