package com.ecom.app.controller;

import com.ecom.app.requestbody.CategoryRequest;
import com.ecom.app.service.CategoryService;
import com.ecom.app.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequest categoryRequest){
        return service.addCategory(categoryRequest);
    }

    @PostMapping("/categories")
    public ResponseEntity<?> addCategories(@RequestBody List<CategoryRequest> categoryRequestList){
        return service.addCategories(categoryRequestList);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> fetchCategoryById(@PathVariable int categoryId){
        return service.fetchCategoryById(categoryId);
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<?> updateCategoryById(@PathVariable int categoryId,@RequestBody CategoryRequest categoryRequest){
        return service.updateCategory(categoryId,categoryRequest);
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable int categoryId){
        return service.deleteCategory(categoryId);
    }


    @GetMapping("/categories/list") public ResponseEntity<?> fetchAllCategories(){
        return service.fetchAllCategory();
    }


}
