package com.blog.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.exception.ResourceNotFoundException;
import com.blog.blog.models.Category;
import com.blog.blog.payloads.CategoryDTO;
import com.blog.blog.repositories.CategoryRepo;
import com.blog.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
    Category cat = this.modelMapper.map(categoryDTO, Category.class);
    Category addedCat = this.categoryRepo.save(cat);

        return this.modelMapper.map(addedCat, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        cat.setCategoryTitle(categoryDTO.getCategoryTitle());
        cat.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category updatecat = this.categoryRepo.save(cat);
        return this.modelMapper.map(updatecat, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDTO getCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));

        return this.modelMapper.map(cat, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = this.categoryRepo.findAll();
     List<CategoryDTO> categoryDTOs =    categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return categoryDTOs;
    }
    
}
