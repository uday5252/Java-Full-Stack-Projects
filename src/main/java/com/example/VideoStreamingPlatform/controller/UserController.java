package com.example.VideoStreamingPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.VideoStreamingPlatform.entity.User;
import com.example.VideoStreamingPlatform.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  UserService us;

  @PostMapping("/user/create")
  public ResponseEntity<User> createUser(@RequestBody User newUser) {
    User createdUser = us.createUser(newUser);
    return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
  }
}
