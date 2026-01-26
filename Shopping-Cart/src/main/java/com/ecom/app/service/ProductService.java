package com.ecom.app.service;

import com.ecom.app.requestbody.CategoryRequest;
import com.ecom.app.requestbody.ProductRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<?> addProduct(ProductRequest productRequest);
    ResponseEntity<?> fetchProductById(int productId);
    ResponseEntity<?> updateProduct(int productId,ProductRequest productRequest);
    ResponseEntity<?> fetchAllProduct();
    ResponseEntity<?> deleteProduct();

    ResponseEntity<?> addProducts(List<ProductRequest> productRequestList);

    ResponseEntity<?> fetchAllProductByCategory(int categoryId);
}
