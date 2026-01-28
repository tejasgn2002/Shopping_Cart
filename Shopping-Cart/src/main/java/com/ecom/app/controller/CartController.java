package com.ecom.app.controller;

import com.ecom.app.requestbody.CartRequest;
import com.ecom.app.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
     @Autowired
    private CartService service;

    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody String username){
        return service.addCart(username);
    }

    @GetMapping("/total/{cartId}")
    public ResponseEntity<?> fetchTotalOfCartItems(@PathVariable int cartId){
        return service.fetchTotalOfCartItems(cartId);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> fetchCartIdByUsername(@PathVariable String username){
        return service.fetchCartIdByUsername(username);
    }
}
