package com.example.demo.ZeeVideoStreaming.KAFKATOPICS;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class CreateKafkaTopics {

	public NewTopic createUserLikesTopic() {
		return TopicBuilder.name("user_likes").build();
	}

	public NewTopic createUserCommentsTopic() {
		return TopicBuilder.name("user_comments").build();
	}

	public NewTopic createVideoUploadTopic() {
		return TopicBuilder.name("video_uploads").build();
	}

	public NewTopic createGenreUpdatesTopic() {
		return TopicBuilder.name("genre_updates").build();
	}

}
