package com.ecom.app.exceptions;

public class OutOfStockException extends Exception{
    public OutOfStockException(String message){
        super(message);
    }
}
