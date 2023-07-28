package com.project.ZeeWebApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ZeeWebApplication.Entity.UserEntity;
import com.project.ZeeWebApplication.Repository.UserRepositoryInterface;

@Service
public class UserService {
	
	@Autowired
	UserRepositoryInterface uri;

	public void SaveUserDetails(UserEntity ue) {
		
		uri.save(ue);
		
	}
	
	
}
