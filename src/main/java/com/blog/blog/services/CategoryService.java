package com.blog.blog.services;

import java.util.List;

import com.blog.blog.payloads.CategoryDTO;

public interface CategoryService {
    //create
    public CategoryDTO createCategory(CategoryDTO categoryDTO);

    //update
    public CategoryDTO updateCategory(CategoryDTO categoryDTO,Integer categoryId);
    
    //delete
    public void deleteCategory(Integer categoryId);

    //get
    public CategoryDTO getCategory(Integer categoryId);

    //get All
    List<CategoryDTO> getCategories();

}
