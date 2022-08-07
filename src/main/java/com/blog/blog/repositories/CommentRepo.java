package com.blog.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.models.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
    
}
