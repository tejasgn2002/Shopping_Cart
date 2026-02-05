package com.ecom.app.service;


import com.ecom.app.requestbody.CategoryRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CategoryService {
    ResponseEntity<?> addCategory(CategoryRequest categoryRequest);
    ResponseEntity<?> fetchCategoryById(int categoryId);
    ResponseEntity<?> updateCategory(int categoryId,CategoryRequest categoryRequest);
    ResponseEntity<?> fetchAllCategory();
    ResponseEntity<?> deleteCategory(int categoryId);

    ResponseEntity<?> addCategories(List<CategoryRequest> categoryRequestList);
}
