package com.ecom.app.service;

import com.ecom.app.entity.CartItem;
import com.ecom.app.requestbody.CartItemRequest;
import org.springframework.http.ResponseEntity;

public interface CartItemService {
    ResponseEntity<?> fetchCartItemById(int cartItemId);
    ResponseEntity<?> fetchAllCartItems();
    ResponseEntity<?> deleteCartItem(int itemId);

    ResponseEntity<?> addCartItem(int cartId, CartItemRequest cartItemRequest);

    ResponseEntity<?> updateQuantity(int cartId, int itemId, int quantity);
}
