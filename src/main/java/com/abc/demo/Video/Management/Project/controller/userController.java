package com.abc.demo.Video.Management.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.Video.Management.Project.entity.userEntity;
import com.abc.demo.Video.Management.Project.service.userService;

@RestController
public class userController {
	@Autowired
	userService us;
	
    @PostMapping("/user/register")
	public ResponseEntity<String> register(@RequestBody userEntity ue)
	{
		us.registerUser(ue);
		return new ResponseEntity<>("created", HttpStatus.CREATED);
	}
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> delete(@PathVariable ("id") int id)
    {
    	us.deleteUser(id);
    	return new ResponseEntity<>("Deleted", HttpStatus.CREATED);
    }
    @PutMapping("/updateUser/{id}")
	public ResponseEntity<String> update(@PathVariable ("id") int id,@RequestBody userEntity ue)
	{
		us.updateUser(id,ue);
		return new ResponseEntity<>("updated", HttpStatus.CREATED);
	}
}
