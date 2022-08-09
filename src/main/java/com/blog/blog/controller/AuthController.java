package com.blog.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.exception.ApiException;
import com.blog.blog.payloads.JwtAuthRequest;
import com.blog.blog.payloads.JwtAuthResponse;
import com.blog.blog.payloads.UserDTO;
import com.blog.blog.security.JwtTokenHelper;
import com.blog.blog.services.UserService;

@RestController 
@RequestMapping("/api/auth/")
public class AuthController {
    
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;


    
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{

        this.authenticate(request.getUsername(),request.getPassword());
    UserDetails userDetails =     this.userDetailsService.loadUserByUsername(request.getUsername());
    String token =     this.jwtTokenHelper.generateToken(userDetails);
     
    JwtAuthResponse response = new JwtAuthResponse();
    response.setToken(token);
    return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
    }



    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try{
        this.authenticationManager.authenticate(authenticationToken);
        }
        catch(BadCredentialsException e){
            System.out.println("Invalid Details");
            throw new ApiException("invalid username or password");
        }
       

    }

    //register new user api
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){
     UserDTO registerUser =  this.userService.registerNewUser(userDTO);
    return new ResponseEntity<UserDTO>(registerUser,HttpStatus.CREATED);
    }
}
