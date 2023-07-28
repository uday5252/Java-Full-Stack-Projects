package com.project.ZeeWebApplication.KafkaTopic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

	@Bean
	public NewTopic createLikeTopic() {
		return TopicBuilder.name("user_likes").build();
	}
	
	@Bean
	public NewTopic createCommentTopic() {
		return TopicBuilder.name("user_comments").build();
	}
	
	@Bean
	public NewTopic createVideoTopic() {
		return TopicBuilder.name("video_uploads").build();
	}
	
	@Bean
	public NewTopic createGenreTopic() {
		return TopicBuilder.name("genre_updates").build();
	}
}
