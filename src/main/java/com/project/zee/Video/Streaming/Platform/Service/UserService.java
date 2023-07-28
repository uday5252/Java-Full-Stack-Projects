package com.project.zee.Video.Streaming.Platform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.zee.Video.Streaming.Platform.Entity.UserEntity;
import com.project.zee.Video.Streaming.Platform.Repository.UserInterface;

@Service
public class UserService {
	
	@Autowired
	UserInterface ui;
	
	public void adduser(UserEntity user)
	{
		ui.save(user);
	}

}
