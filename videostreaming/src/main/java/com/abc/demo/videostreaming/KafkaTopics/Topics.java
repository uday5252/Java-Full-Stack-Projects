package com.abc.demo.videostreaming.KafkaTopics;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Topics {
    @Bean
	NewTopic userlikeTopic(){
		return TopicBuilder.name("user_likes").build();
	}

	@Bean
	NewTopic userCommentsTopic(){
		return TopicBuilder.name("user_comments").build();
	}

	@Bean
	NewTopic videoUploadsTopic(){
		return TopicBuilder.name("video_uploads").build();
	}

	@Bean
	NewTopic genreUpdatesTopic(){
		return TopicBuilder.name("genre_updates").build();
	}
}
