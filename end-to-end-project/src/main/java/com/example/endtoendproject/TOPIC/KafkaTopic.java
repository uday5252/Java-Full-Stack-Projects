package com.example.endtoendproject.TOPIC;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaTopic {
	
//		public NewTopic createTopic(){
//		       return TopicBuilder.name("video-topic").build();
//		    }
		@Bean
		public NewTopic createTopic1() {
			return TopicBuilder.name("user_likes").build();
		}
		@Bean
		public NewTopic createTopic2() {
			return TopicBuilder.name("user_comments").build();
		}
		@Bean
		public NewTopic createTopic3() {
			return TopicBuilder.name("video_uploads").build();
		}
		@Bean
		public NewTopic createTopic4() {
			return TopicBuilder.name("genre_updates").build();}
		
}
