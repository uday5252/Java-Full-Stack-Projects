package com.example.VideoStreamingPlatform.topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Topics {
  @Bean
  public NewTopic firstTopic() {
    return TopicBuilder.name("user_likes").build();
  }

  @Bean
  public NewTopic secondTopic() {
    return TopicBuilder.name("user_comments").build();
  }

  @Bean
  public NewTopic thirdTopic() {
    return TopicBuilder.name("video_uploads").build();
  }

  @Bean
  public NewTopic fourthTopic() {
    return TopicBuilder.name("genre_updates").build();
  }
}
