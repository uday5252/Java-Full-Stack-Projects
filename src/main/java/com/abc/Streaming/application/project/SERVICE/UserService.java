package com.abc.Streaming.application.project.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.Streaming.application.project.ENTITIES.UserEntity;
import com.abc.Streaming.application.project.REPOSITORIES.UserEntityInterfaceRepositary;

import jakarta.persistence.Id;

@Service
public class UserService {
	
	@Autowired
	UserEntityInterfaceRepositary u;
	
	public void addUser(UserEntity ue)
	{
		u.save(ue);
	}

}
