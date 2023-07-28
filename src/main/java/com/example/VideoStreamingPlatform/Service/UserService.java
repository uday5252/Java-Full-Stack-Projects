package com.example.VideoStreamingPlatform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;

import com.example.VideoStreamingPlatform.Entity.UserEntity;
import com.example.VideoStreamingPlatform.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	KafkaAdmin admin;
	
	@Autowired
	KafkaTemplate<String, String> kt;
	
	public void addUser(UserEntity user) {
		ur.save(user);
		String User = "create_user_topic";
		admin.createOrModifyTopics(TopicBuilder.name(User).build());
		kt.send(User, "A new user has been created with id:"+user.getId());
	}
}
