package com.videostreamingapp.com.Assignment.project.CONTROLLER;

import com.videostreamingapp.com.Assignment.project.ENTITY.UserEntity;
import com.videostreamingapp.com.Assignment.project.SERVICE.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService ps;
    @PostMapping("/user/create")
    public ResponseEntity<String> createUser(@RequestBody UserEntity user){
        ps.userCreated(user);
        return new ResponseEntity<String>("User has been created", HttpStatus.CREATED);
    }
}
