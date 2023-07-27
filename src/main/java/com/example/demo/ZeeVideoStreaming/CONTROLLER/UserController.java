package com.example.demo.ZeeVideoStreaming.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ZeeVideoStreaming.ENTITY.UserEntity;
import com.example.demo.ZeeVideoStreaming.SERVICE.UserService;

@RestController
public class UserController {

	@Autowired
	UserService us;

	@PostMapping("/users/create")
	public ResponseEntity<Object> createUser(@RequestBody UserEntity user) {
		us.addUserInDatabase(user);
		return ResponseHandler.generateResponse("user created", HttpStatus.CREATED, user, true);
	}
}
