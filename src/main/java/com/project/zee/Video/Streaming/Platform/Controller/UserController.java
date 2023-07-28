package com.project.zee.Video.Streaming.Platform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.zee.Video.Streaming.Platform.Entity.UserEntity;
import com.project.zee.Video.Streaming.Platform.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class UserController {
	
	@Autowired
	UserService us;
	
	@ApiResponse
    (
        responseCode = "201"
    )
    @Operation
    (
        summary = "Enter User data",

        description = "Register the user by entering the username , password and email "
    )
	
	@PostMapping("/user/add")
	public ResponseEntity<String> RegisterUser(@RequestBody UserEntity user)
	{
		us.adduser(user);
		return new ResponseEntity<>("User Registered Succesfully" ,HttpStatus.CREATED);
	}
	
	

}
