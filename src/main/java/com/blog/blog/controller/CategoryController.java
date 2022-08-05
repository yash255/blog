package com.blog.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.payloads.CategoryDTO;
import com.blog.blog.services.CategoryService;
import com.blog.blog.utils.ApiResponse;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
      CategoryDTO createCategory = this.categoryService.createCategory(categoryDTO);
      return new ResponseEntity<CategoryDTO>(createCategory,HttpStatus.CREATED);
    }

    //update
    @PutMapping("/update/{catId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Integer catId){
    CategoryDTO updCategoryDTO = this.categoryService.updateCategory(categoryDTO, catId);
    return new ResponseEntity<CategoryDTO>(updCategoryDTO,HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully",true), HttpStatus.OK);
    }


    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer catId){
      CategoryDTO categoryDTO = this.categoryService.getCategory(catId);
      return new ResponseEntity<CategoryDTO>(categoryDTO,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
    List<CategoryDTO> categories =   this.categoryService.getCategories();
    return ResponseEntity.ok(categories);
    }





    
}
