package com.project.videostreamingproject.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.videostreamingproject.entity.User;
import com.project.videostreamingproject.repository.UserRepository;

@Service
public class UserService {
   
	@Autowired
	UserRepository Ur;
	
	public void newuser(User u)
	{  
		u.setCreatedTime(LocalDateTime.now());
		u.setUpdatedTime(LocalDateTime.now());
		Ur.save(u);
	}
	
	public User find(int id)
	{
		return Ur.findById(id).get();
	}
	public void deleteuser(int id)
	{
		Ur.deleteById(id);
	}
	
}
