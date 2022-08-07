package com.blog.blog.payloads;

import java.util.HashSet;
import java.util.Set;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

private int id;


private String name;


private String email;


private String password;


private String about;

private Set<CommentDTO> comments = new HashSet<>();


}


