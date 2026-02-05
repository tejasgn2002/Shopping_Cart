package com.ecom.app.controller;

import com.ecom.app.requestbody.CartItemRequest;
import com.ecom.app.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartItemController {
    @Autowired
    private CartItemService service;

    @PostMapping("/{cartId}/item")
    public ResponseEntity<?> addCartItem(@PathVariable int cartId,@RequestBody CartItemRequest cartItemRequest){
        return service.addCartItem(cartId,cartItemRequest);
    }

    @PatchMapping("/{cartId}/item/{itemId}")
    public ResponseEntity<?> updateQuantity(@PathVariable int cartId,@PathVariable int itemId,@RequestBody int quantity){
        return service.updateQuantity(cartId,itemId,quantity);
    }

    @DeleteMapping("/{cartId}/item/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable int cartId,@PathVariable int itemId){
        return service.deleteCartItem(itemId);
    }
}
