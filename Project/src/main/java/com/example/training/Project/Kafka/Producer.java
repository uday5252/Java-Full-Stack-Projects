package com.example.training.Project.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    @Autowired
	KafkaTemplate<Integer, String> kt;

	public void sendmessage()
	{
	  kt.send("video_uploads","video Uploaded successfully");	
	}
	public void sendmessage2()
	{
	  kt.send("user_likes","User has liked");	
	}
	public void sendmessage3()
	{
	  kt.send("user_comments","user has commented");	
	}
	public void sendmessage4()
	{
	  kt.send("genre_updates","Genre updated succesfully");	
	}
}
