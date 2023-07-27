package com.example.training.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.training.Project.Entities.UserEntity;
import com.example.training.Project.Services.UserService;

@RestController
public class UserController {
    
    @Autowired
    UserService uService;

    @PostMapping("/api/user")
    public ResponseEntity<String> createGenre(@RequestBody UserEntity user){
        uService.createUser(user);
        return new ResponseEntity<>("user created successfully!",HttpStatus.OK);
    }
}
