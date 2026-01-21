package com.ecom.app.controller;

import com.ecom.app.requestbody.UserRequest;
import com.ecom.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class SuperAdminController {
    @Autowired
    private UserService service;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest){
        userRequest.setRole("ROLE_SUPER-ADMIN");
        return service.addUser(userRequest);
    }
}
