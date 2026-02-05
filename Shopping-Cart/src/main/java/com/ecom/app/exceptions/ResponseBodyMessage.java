package com.ecom.app.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseBodyMessage {
    public static Map<String, Object> response(Exception ex,int value){
        Map<String, Object> response = new HashMap<>();
        response.put("status", value);
        response.put("error", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return response;
    }
}
