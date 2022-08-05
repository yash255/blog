package com.blog.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.models.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{
    
}
