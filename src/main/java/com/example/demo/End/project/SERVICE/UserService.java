package com.example.demo.End.project.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.End.project.ENTITY.UserEntity;
import com.example.demo.End.project.REPOSITORY.UserRepositoryInterface;

@Service
public class UserService {

	@Autowired
	UserRepositoryInterface uri;

	public void createUser(UserEntity ue) {
	uri.save(ue);
	}

	@Autowired
	KafkaTemplate<Integer,String> kt;
	
	public void sendDataToUserLikes(String message) {
		kt.send("User_likes",message);
	}
	public void sendDataToUserComments(String message) {
		kt.send("User_Comments",message);
	}
	public void sendDataToVideoUpload(String message) {
		kt.send("Video_upload",message);
	}
	public void sendDataToGenreUpdates(String message) {
		kt.send("Genre_Updates",message);
	}
	
	
}
