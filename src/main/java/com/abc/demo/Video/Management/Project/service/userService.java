package com.abc.demo.Video.Management.Project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.Video.Management.Project.entity.userEntity;
import com.abc.demo.Video.Management.Project.repository.userInterface;

@Service
public class userService {
	@Autowired
	userInterface ui;
	@Autowired
	KafkaAdmin Admin;
	@Autowired
	KafkaTemplate<String, String> kt;
	public void registerUser(userEntity ue)
	{
		ui.save(ue);
		String User="UserTopic"+ue.getUserid();
		Admin.createOrModifyTopics(TopicBuilder.name(User).build());
		kt.send(User,ue.toString());
	}
	public void deleteUser(int id)
	{
		ui.delete(ui.findById(id).get());
	}
	public void updateUser(int id,userEntity ue)
	{
		userEntity user=ui.findById(id).get();
		user.setEmail(ue.getEmail()!=null ? ue.getEmail() : user.getEmail());
		user.setPassword(ue.getPassword()!=null ? ue.getPassword() : user.getPassword());
		user.setUsername(ue.getUsername()!=null ? ue.getUsername() : user.getPassword());
		ui.save(user);
	}
}
