package com.abc.demo.END_PROJECT.Apache_Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopics {
	
	@Bean
	public NewTopic createFirstTopic() {
		return TopicBuilder.name("user_likes").partitions(100).build();
	}
	
	@Bean
	public NewTopic createSecondTopic() {
		return TopicBuilder.name("user_comments").partitions(100).build();
	}
	@Bean
	public NewTopic createThirdTopic() {
		return TopicBuilder.name("video_uploads").partitions(100).build();
	}
	
	@Bean
	public NewTopic createFourthTopic() {
		return TopicBuilder.name("genre_updates").partitions(100).build();
	}
	

}
