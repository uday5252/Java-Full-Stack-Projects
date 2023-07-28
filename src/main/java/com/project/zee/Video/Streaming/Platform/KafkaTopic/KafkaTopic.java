package com.project.zee.Video.Streaming.Platform.KafkaTopic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

	@Bean
	public NewTopic LikesTopic()
	{
		return TopicBuilder.name("likesDataTopic").partitions(100).build();
	}

	@Bean
	public NewTopic CommentsTopic()
	{
		return TopicBuilder.name("CommentsDataTopic").partitions(100).build();
	}

	@Bean
	public NewTopic VideosTopic()
	{
		return TopicBuilder.name("VideosDataTopic").partitions(100).build();
	}

	@Bean
	public NewTopic GenreTopic()
	{
		return TopicBuilder.name("GenreDataTopic").partitions(100).build();
	}
	
}
