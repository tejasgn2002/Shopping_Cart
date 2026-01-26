package com.ecom.app.exceptions;

public class CartAlreadyExistsException extends RuntimeException{
    public CartAlreadyExistsException(String message){
        super(message);
    }
}
