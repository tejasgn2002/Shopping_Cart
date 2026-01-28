package com.ecom.app.controller;

import com.ecom.app.requestbody.ProductRequest;
import com.ecom.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest){
        return service.addProduct(productRequest);
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProducts(@RequestBody List<ProductRequest> productRequestList){
        return service.addProducts(productRequestList);
    }

    @GetMapping("/products")
    public ResponseEntity<?> fetchAllProductByCategory(@RequestParam int categoryId){
        return service.fetchAllProductByCategory(categoryId);
    }

    @GetMapping("/products/list")
    public ResponseEntity<?> fetchAllProduct(){
        return service.fetchAllProduct();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> fetchProductById(int productId){
        return service.fetchProductById(productId);
    }
}
