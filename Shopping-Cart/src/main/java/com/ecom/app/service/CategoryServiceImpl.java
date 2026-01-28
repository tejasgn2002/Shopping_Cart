package com.ecom.app.service;

import com.ecom.app.entity.Category;
import com.ecom.app.entity.Product;
import com.ecom.app.exceptions.CategoryNotFoundException;
import com.ecom.app.repository.CategoryRepository;
import com.ecom.app.requestbody.CategoryRequest;
import com.ecom.app.requestbody.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger =
            LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public ResponseEntity<?> addCategory(CategoryRequest categoryRequest) {

        logger.info("Add category request received: categoryName={}",
                categoryRequest.getCategoryName());

        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName());

        logger.info("Saving category with name={}", categoryRequest.getCategoryName());

        return new ResponseEntity<>(categoryRepo.save(category), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addCategories(List<CategoryRequest> categoryRequestList) {

        logger.info("Add multiple categories request received. Count={}",
                categoryRequestList.size());

        List<Category> categories = new ArrayList<>();

        categoryRequestList.forEach(categoryRequest -> {
            logger.debug("Preparing category: categoryName={}",
                    categoryRequest.getCategoryName());
            categories.add(new Category(null, categoryRequest.getCategoryName(), null));
        });

        logger.info("Saving {} categories", categories.size());

        return new ResponseEntity<>(categoryRepo.saveAll(categories), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> fetchCategoryById(int categoryId) {

        logger.info("Fetch category by id request received: categoryId={}", categoryId);

        return new ResponseEntity<>(categoryRepo.findById(categoryId).orElseThrow(
                ()->{
                    return new CategoryNotFoundException("Category Not Found");
                }
        ),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateCategory(int categoyrId, CategoryRequest categoryRequest) {

        logger.info("Update category request received: categoryId={}, categoryName={}",
                categoyrId, categoryRequest.getCategoryName());

        return null;
    }

    @Override
    public ResponseEntity<?> fetchAllCategory() {

        logger.info("Fetch all categories request received");
        List<String> categories = categoryRepo.findAll()
                                                .stream()
                                                .map(Category::getCategoryName)
                                                .toList();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteCategory() {

        logger.info("Delete category request received");

        return null;
    }
}
