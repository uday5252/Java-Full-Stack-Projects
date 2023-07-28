package com.xyz.demo.EndProject.Topics;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class CreateTopics {
	/*
	 * 
	 * 1. `user_likes` 2. `user_comments` 3. `video_uploads` 4. `genre_updates`
	 */

	@Bean
	public NewTopic UserLikesTopic() {
		return TopicBuilder.name("user_likes").build();
	}

	@Bean
	public NewTopic UserCommentsTopic() {
		return TopicBuilder.name("user_comments").build();
	}

	@Bean
	public NewTopic VideoUploadTopic() {
		return TopicBuilder.name("video_uploads").build();
	}

	@Bean
	public NewTopic GenreUpdatesTopic() {
		return TopicBuilder.name("genre_updates").build();
	}
}
