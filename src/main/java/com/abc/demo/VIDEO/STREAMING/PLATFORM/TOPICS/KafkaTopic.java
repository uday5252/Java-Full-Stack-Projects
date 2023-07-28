package com.abc.demo.VIDEO.STREAMING.PLATFORM.TOPICS;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
	@Bean
	public  NewTopic TopicOne() {
		
	 return	TopicBuilder.name("video_uploads").partitions(100).build();
		
	}
	
	@Bean
	public  NewTopic TopicTwo() {
		
		 return	TopicBuilder.name("user_comments").partitions(100).build();
			
	}
	
	@Bean
	public  NewTopic TopicThree() {
		
		 return	TopicBuilder.name("user_likes").partitions(100).build();
			
	}
	
	@Bean
	public  NewTopic TopicFour() {
		
		 return	TopicBuilder.name("genre_updates").partitions(100).build();
	}


}
