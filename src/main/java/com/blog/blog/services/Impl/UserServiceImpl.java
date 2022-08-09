package com.blog.blog.services.Impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.blog.config.AppConstants;
import com.blog.blog.exception.ResourceNotFoundException;
import com.blog.blog.models.Role;
import com.blog.blog.models.User;
import com.blog.blog.payloads.UserDTO;
import com.blog.blog.repositories.RoleRepo;
import com.blog.blog.repositories.UserRepo;
import com.blog.blog.services.UserService;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

@Autowired
private UserRepo userRepo;

@Autowired
private ModelMapper modelMapper;

@Autowired
private PasswordEncoder passwordEncoder;

@Autowired
private RoleRepo roleRepo;


@Override
public UserDTO createUser(UserDTO userDto) {
    User user = this.dtoToUser(userDto);
    User savedUser = this.userRepo.save(user);
    return userToDto(savedUser);
}

@Override
public UserDTO updateUser(UserDTO userDTO, Integer userId) {
   
    User user  = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
    user.setName(userDTO.getName());
    user.setEmail(userDTO.getEmail());
    user.setPassword(userDTO.getPassword());
    user.setAbout(userDTO.getAbout());

    User updateUser = this.userRepo.save(user);
    UserDTO uDto = this.userToDto(updateUser);
    return uDto;
}

@Override
public UserDTO getUserById(Integer userId) {
    User user  = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

    return this.userToDto(user);
}

@Override
public List<UserDTO> getAllUsers() {
   List<User> users =  this.userRepo.findAll();
 List<UserDTO> userDTOs =   users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
    return userDTOs;
}

@Override
public void deleteUser(Integer userId) {
    User user  = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
this.userRepo.delete(user);
    
}

  

public User dtoToUser(UserDTO userDTO){
    User user  = this.modelMapper.map(userDTO, User.class);
    // user.setId(userDTO.getId());
    // user.setName(userDTO.getName());
    // user.setAbout(userDTO.getAbout());
    // user.setEmail(userDTO.getEmail());
    // user.setPassword(userDTO.getPassword());
    return user;
}
public UserDTO userToDto(User user){

    UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
    // userDTO.setAbout(user.getAbout());
    // userDTO.setEmail(user.getEmail());
    // userDTO.setId(user.getId());
    // userDTO.setName(user.getName());
    // userDTO.setPassword(user.getPassword());
    return userDTO;

}

@Override
public UserDTO registerNewUser(UserDTO userDTO) {
    User user = this.modelMapper.map(userDTO, User.class);

    // encoded the password
    user.setPassword(this.passwordEncoder.encode(user.getPassword()));

    // roles
    Role role = this.roleRepo.findById(AppConstants.ROLE_NORMAL).get();

    user.getRoles().add(role);

    User newUser = this.userRepo.save(user);

    return this.modelMapper.map(newUser, UserDTO.class);
}
    
}