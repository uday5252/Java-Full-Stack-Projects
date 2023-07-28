package com.project.ZeeWebApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ZeeWebApplication.Entity.UserEntity;
import com.project.ZeeWebApplication.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
		name = "User API controller",
		description = "This contains all the End points of the user Application"
		)

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
		summary = "Insert User data",
		description = "Insert the User data form user side"
	)
	@PostMapping("/adduser/data")
	public ResponseEntity<String> AddUserDetails(@RequestBody UserEntity ue)
	{
		
		us.SaveUserDetails(ue);
		
		return new ResponseEntity<>("Added user information sucessfully",HttpStatus.ACCEPTED);
	}
	
}
