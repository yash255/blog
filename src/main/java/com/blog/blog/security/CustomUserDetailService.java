package com.blog.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.blog.exception.ResourceNotFoundException;
import com.blog.blog.models.User;
import com.blog.blog.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{
    
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        //loading user from database by username
    User user =    this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "email "+username, 0));
        return user;
    }
    
    
}
