package com.blog.blog.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



import com.blog.blog.models.User;

public interface UserRepo extends JpaRepository<User,Integer>{
    
    Optional<User> findByEmail(String email);
}
