package com.abc.demo.VIDEO.STREAMING.PLATFORM.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.userEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE.userService;

@RestController
public class userController {
	
	@Autowired
	userService us;
	
	@PostMapping("/user/register")
	public ResponseEntity<String> registerUser(@RequestBody userEntity ue) {
		
		us.register(ue);
		
	return new ResponseEntity<>("registered successfully",HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/user/{userId}/delete")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") int Id) {
		
		us.delete(Id);	
		
		return new ResponseEntity<>("deleted successfully",HttpStatus.CREATED);	
	}
	
	@PutMapping("user/{userId}/update")
	public ResponseEntity<String> updateUser(@PathVariable("userId") int id,@RequestBody userEntity updatedData) {
		
		us.update(id,updatedData);
		
	return new ResponseEntity<>("updated successfully",HttpStatus.CREATED);	
	}
	
	@GetMapping("user/{userId}")
	public ResponseEntity<userEntity> getUser(@PathVariable("userId") int id) {
		userEntity user=us.getUserById(id);
		
	return new ResponseEntity<>(user,HttpStatus.CREATED);	
		
	}
	

}
