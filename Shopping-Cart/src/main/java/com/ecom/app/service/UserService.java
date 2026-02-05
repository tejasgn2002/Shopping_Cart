package com.ecom.app.service;


import com.ecom.app.requestbody.UserRequest;
import org.springframework.http.ResponseEntity;


public interface UserService {
    ResponseEntity<?> fetchAllUsers();
    ResponseEntity<?> addUser(UserRequest userRequest);
    ResponseEntity<?> updateUserDetails(String username,UserRequest userRequest);
    ResponseEntity<?> fetchUserByUserName(String username);

    ResponseEntity<?> loginUser(String username, String password);
}
