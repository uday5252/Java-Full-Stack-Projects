package com.example.training.Project.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Topics {
    @Bean
	public NewTopic createVideoUploadTopic()
	{
		return TopicBuilder.name("video_uploads").build();
	}
	@Bean
	public NewTopic createUserlikeTopic()
	{
		return TopicBuilder.name("user_likes").build();
	}
	@Bean
	public NewTopic createuserCommentsTopic()
	{
		return TopicBuilder.name("user_comments").build();
	}
	@Bean
	public NewTopic createGenreupdatesTopic()
	{
		return TopicBuilder.name("genre_updates").build();
	}
}
