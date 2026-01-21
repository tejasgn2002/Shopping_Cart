package com.ecom.app.service;

import com.ecom.app.repository.CategoryRepository;
import com.ecom.app.requestbody.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    ResponseEntity<?> addCategory(CategoryRequest categoryRequest);
    ResponseEntity<?> fetchCategoryById(int categoryId);
    ResponseEntity<?> updateCategory(int categoyrId,CategoryRequest categoryRequest);
    ResponseEntity<?> fetchAllCategory();
    ResponseEntity<?> deleteCategory();

    ResponseEntity<?> addCategories(List<CategoryRequest> categoryRequestList);
}
