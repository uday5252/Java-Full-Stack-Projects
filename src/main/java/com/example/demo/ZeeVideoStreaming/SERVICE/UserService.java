package com.example.demo.ZeeVideoStreaming.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ZeeVideoStreaming.ENTITY.UserEntity;
import com.example.demo.ZeeVideoStreaming.REPOSITORY.UserRepositoryInterface;

@Service
public class UserService {
	
	@Autowired
	UserRepositoryInterface uri;
	
	public void addUserInDatabase(UserEntity user) {
		uri.save(user);
	}
	
	public UserEntity getUserFromDatabase(long id) {
		return uri.findById(id).get();
	}
}
