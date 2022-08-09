package com.blog.blog.services;

import java.util.List;


import com.blog.blog.payloads.UserDTO;

public interface UserService {

    UserDTO registerNewUser(UserDTO user);    
    UserDTO createUser(UserDTO userDto);
    UserDTO updateUser(UserDTO userDTO,Integer userId);
    UserDTO getUserById(Integer userId);
    List<UserDTO> getAllUsers();
    void deleteUser(Integer userId);
}
