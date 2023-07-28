package com.project.demo.end_to_end.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.end_to_end.entities.User;
import com.project.demo.end_to_end.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "User Controller",description = "This has end points to maniputlate with user")
@RestController
public class UserController {
    @Autowired
    UserService us;
    @PostMapping("/users/add")
    public ResponseEntity<User> addUser(@RequestBody User user){ 
        us.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
    @PostMapping("/users/update")
    public ResponseEntity<?> updateUser(@RequestBody User user,String username,String password){
        User userFromTable = us.findbycredentials(username,password);
        if(userFromTable == null)
            return new ResponseEntity<String>("invalidCredentials",HttpStatus.NOT_ACCEPTABLE);
        user.setId(userFromTable.getId());
        user.setCreatedAt(userFromTable.getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now());
        if(user.getUsername() == null)  user.setUsername(userFromTable.getUsername());
        if(user.getPassword() == null)  user.setPassword(userFromTable.getPassword());
        if(user.getEmail() == null) user.setEmail(userFromTable.getEmail());
        us.addUser(user);
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }
}
