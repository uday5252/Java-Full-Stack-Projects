package com.example.demo.End.project.KAFKA;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
	@Bean
	public NewTopic UserLikes()
	{
		return TopicBuilder.name("User_likes").build();
	}
	@Bean
	public NewTopic UserComments()
	{
		return TopicBuilder.name("User_Comments").build();
	}
	@Bean
	public NewTopic VideoUpload()
	{
		return TopicBuilder.name("Video_upload").build();
	}
	@Bean
	public NewTopic GenreUpdates()
	{
		return TopicBuilder.name("li").build();
	}

}
