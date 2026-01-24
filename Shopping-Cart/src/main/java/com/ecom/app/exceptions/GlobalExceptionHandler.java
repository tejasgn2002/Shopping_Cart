package com.ecom.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<?> handleCartNotFound(CartNotFoundException ex) {
        return new ResponseEntity<>(ResponseBodyMessage.response(ex), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFound(ProductNotFoundException ex) {
        return new ResponseEntity<>(ResponseBodyMessage.response(ex), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<?> handleCartItemNotFound(CartItemNotFoundException ex) {
        return new ResponseEntity<>(ResponseBodyMessage.response(ex), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CartAlreadyExistsException.class)
    public ResponseEntity<?> handleCartAlreadyExists(CartAlreadyExistsException ex) {
        return new ResponseEntity<>(ResponseBodyMessage.response(ex), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(ResponseBodyMessage.response(ex), HttpStatus.CONFLICT);
    }

}
