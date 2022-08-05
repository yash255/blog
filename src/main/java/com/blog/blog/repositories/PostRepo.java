package com.blog.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.models.Category;
import com.blog.blog.models.Post;
import com.blog.blog.models.User;

/**
 * PostRepo
 */
public interface PostRepo extends JpaRepository<Post,Integer>{

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}