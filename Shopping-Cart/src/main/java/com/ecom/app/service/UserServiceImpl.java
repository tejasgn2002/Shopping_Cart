package com.ecom.app.service;

import com.ecom.app.entity.Role;
import com.ecom.app.entity.User;
import com.ecom.app.exceptions.UserNotFoundException;
import com.ecom.app.exceptions.UsernameAlreadyExistsException;
import com.ecom.app.repository.RoleRepository;
import com.ecom.app.repository.UserRepository;
import com.ecom.app.requestbody.UserRequest;
import com.ecom.app.util.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger =
            LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Override
    public ResponseEntity<?> fetchAllUsers() {

        logger.info("Fetch all users request received");

        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addUser(UserRequest userRequest) {

        logger.info("Add user request received: username={}, role={}",
                userRequest.getUsername(),
                userRequest.getRole());

        if (userRepo.findByUsername(userRequest.getUsername()).isPresent()) {
            logger.warn("Username already exists: username={}",
                    userRequest.getUsername());
            throw new UsernameAlreadyExistsException( "Username Already Exist");
        }

        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setPhone(userRequest.getPhone());
        user.setUsername(userRequest.getUsername());
        user.setPassword(encoder.encode(userRequest.getPassword()));

        Role role = roleRepo.findByRoleName(userRequest.getRole());
        user.setRole(role);

        logger.info("Saving user: username={}, role={}",
                user.getUsername(),
                userRequest.getRole());

        return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateUserDetails(String username, UserRequest userRequest) {

        logger.info("Update user request received: username={}", username);

        User user = userRepo.findByUsername(username).orElseThrow(()->{
            return new UserNotFoundException("User Not Found");
        });

        user.setName(userRequest.getName()); // == null?user.getName():userRequest.getName());
        user.setAge(userRequest.getAge()); // == 0?user.getAge():userRequest.getAge());
        user.setPhone(userRequest.getPhone()); // == 0?user.getPhone():userRequest.getPhone());

        logger.info("Updating user details for username={}", username);

        return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> fetchUserByUserName(String username) {

        logger.info("Fetch user by username request received: username={}", username);
        User user = userRepo.findByUsername(username).orElseThrow(()->{
            return new UserNotFoundException("User Not Found");
        });
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> loginUser(String username, String password) {
        logger.info("Login user by username request received: username={}", username);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password)
        );

        if(authentication.isAuthenticated()){
            logger.info("User Login Successfully for username={}", username);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String accessToken = jwtService.generateToken(userDetails);
            Map<String, String> tokens = new TreeMap<>();
            tokens.put("accessToken", accessToken);
            return ResponseEntity.ok(tokens);
        }
        else{
            System.out.println("Failed");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password");
    }
}
