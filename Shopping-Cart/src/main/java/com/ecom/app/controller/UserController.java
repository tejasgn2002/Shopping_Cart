package com.ecom.app.controller;

import com.ecom.app.entity.User;
import com.ecom.app.requestbody.UserLoginRequest;
import com.ecom.app.requestbody.UserRequest;
import com.ecom.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> fetchAllUsers(){
        return service.fetchAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest){
        userRequest.setRole("ROLE_USER");
        return service.addUser(userRequest);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUserDetails(@PathVariable String username,@RequestBody UserRequest userRequest){
        return service.updateUserDetails(username,userRequest);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> fetchUserByUsername(@PathVariable String username){
        return service.fetchUserByUserName(username);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userLoginRequest){
        return service.loginUser(userLoginRequest.getUsername(),userLoginRequest.getPassword());
    }

}
