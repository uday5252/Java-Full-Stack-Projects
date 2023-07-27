package com.abc.demo.Final.Project.implementing.all.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.Final.Project.implementing.all.Entity.UserEntity;
import com.abc.demo.Final.Project.implementing.all.SERVICE.UserService;

@RestController
public class UserController {

	@Autowired
	UserService us;
	
	@PostMapping("/user/add")
	public ResponseEntity<String> addUser(@RequestBody UserEntity ue){
		us.userAdd(ue);
		return new ResponseEntity<>("User added Successfully",HttpStatus.CREATED);
	}
	
}
