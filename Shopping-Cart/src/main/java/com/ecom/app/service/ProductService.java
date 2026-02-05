package com.ecom.app.service;

import com.ecom.app.requestbody.ProductRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<?> addProduct(ProductRequest productRequest);
    ResponseEntity<?> fetchProductById(int productId);
    ResponseEntity<?> updateProduct(int productId,ProductRequest productRequest);
    ResponseEntity<?> fetchAllProduct();
    ResponseEntity<?> deleteProduct(int productId);

    ResponseEntity<?> addProducts(List<ProductRequest> productRequestList);

    ResponseEntity<?> fetchAllProductByCategory(int categoryId);
}
