package com.abc.demo.Zee5.OTT.Project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import com.abc.demo.Zee5.OTT.Project.Entity.User;
import com.abc.demo.Zee5.OTT.Project.Repository.UserRepository;
@Service
public class UserService {
	@Autowired
	UserRepository ur;
	@Autowired 
	KafkaAdmin Admin;
	public void  createUser(User user)
	{
		ur.save(user);
		String User="user"+user.getUser_id();
		Admin.createOrModifyTopics(TopicBuilder.name(User).build());
		
	}
	public User findUser(int user_id)
	{
		return ur.findById(user_id).get();
	}
	
}
