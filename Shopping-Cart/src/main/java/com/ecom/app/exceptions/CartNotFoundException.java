package com.ecom.app.exceptions;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String message){
        super(message);
    }
}
