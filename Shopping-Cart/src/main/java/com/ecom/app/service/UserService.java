package com.ecom.app.service;

import com.ecom.app.entity.Role;
import com.ecom.app.entity.User;
import com.ecom.app.repository.RoleRepository;
import com.ecom.app.repository.UserRepository;
import com.ecom.app.requestbody.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface UserService {
    ResponseEntity<?> fetchAllUsers();
    ResponseEntity<?> addUser(UserRequest userRequest);
    ResponseEntity<?> updateUserDetails(String username,UserRequest userRequest);
    ResponseEntity<?> fetchUserByUserName(String username);

    ResponseEntity<?> loginUser(String username, String password);
}
