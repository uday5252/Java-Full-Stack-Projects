package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Controller", description = "This controller controls all the endpoints associated with the User functions related to the application")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create/user")
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.collectUserDetails(user);

        return ResponseEntity.ok().body("Created user Succesfully");

    }
}
