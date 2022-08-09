package com.blog.blog.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.payloads.UserDTO;
import com.blog.blog.services.UserService;
import com.blog.blog.utils.ApiResponse;



@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    //POST-create user
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO creatUserDTO  = this.userService.createUser(userDTO);
        return new ResponseEntity<>(creatUserDTO,HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable("userId") Integer userId){
    UserDTO updateUser =   this.userService.updateUser(userDTO, userId);
    return ResponseEntity.ok(updateUser);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully",true),HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

}
