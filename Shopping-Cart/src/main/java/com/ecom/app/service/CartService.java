package com.ecom.app.service;

import com.ecom.app.requestbody.CartRequest;
import com.ecom.app.requestbody.CategoryRequest;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addCart(String username);
    ResponseEntity<?> fetchCartById(int cartId);
    ResponseEntity<?> fetchAllCarts();
    ResponseEntity<?> deleteCart();

    ResponseEntity<?> fetchTotalOfCartItems(int cartId);

    ResponseEntity<?> fetchCartIdByUsername(String username);
}
