package com.abc.demo.Final.Project.implementing.all.KAFKATOPICS;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

	@Bean
	public NewTopic userLikes() {
		return TopicBuilder.name("UserLikes").partitions(100).build();
		}
	
	@Bean
	public NewTopic userComments() {
		return TopicBuilder.name("UserComments").partitions(100).build();
		}

	
	@Bean
	public NewTopic videoUploads() {
		return TopicBuilder.name("VideoUploads").partitions(100).build();
		}

	
	@Bean
	public NewTopic genreUpdates() {
		return TopicBuilder.name("GenreUpdates").partitions(100).build();
		}

}
