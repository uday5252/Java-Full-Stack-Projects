package com.zee.phani.project.ott.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zee.phani.project.ott.dto.UserRequestDto;
import com.zee.phani.project.ott.dto.UserResponseDto;
import com.zee.phani.project.ott.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/user/add")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDto inDao) {
        try {
            service.saveUserData(inDao);
            return new ResponseEntity<String>("Added Sucessfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.PARTIAL_CONTENT);
        }

    }

    @DeleteMapping("user/delete/{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable("uid") int userId) {
        try {
            service.deleteUser(userId);
            return new ResponseEntity<String>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "user/data/{uid}")
    public ResponseEntity<?> getUserEntity(@PathVariable("uid") int userId) {
        try {
            UserResponseDto user = service.getUser(userId);
            return new ResponseEntity<UserResponseDto>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
