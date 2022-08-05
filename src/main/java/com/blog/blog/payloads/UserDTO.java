package com.blog.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

private int id;

@NotEmpty
@Size(min = 1,message="Username must be 1 character !!")
private String name;

@NotEmpty
@Email(message="Email address is not valid")
private String email;

@NotEmpty
// @Size(min = 8,max=15,message="Password must be minimum of 8 characters and maximum of 15 characters")
@Pattern(regexp = "^[a-zA-Z0-9-!@#$%^&*]{6,12}$",message = "username must be of 6 to 12 length with no special characters")
private String password;

@NotNull
private String about;
}
