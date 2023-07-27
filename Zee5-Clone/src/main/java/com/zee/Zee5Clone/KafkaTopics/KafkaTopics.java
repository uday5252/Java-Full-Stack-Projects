package com.zee.Zee5Clone.KafkaTopics;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaTopics {

    @Bean
    public NewTopic userLike(){
        return TopicBuilder.name("user_likes").build();
    }
    @Bean
    public NewTopic userComments(){
        return TopicBuilder.name("user_comments").build();
    }

    @Bean
    public NewTopic videoUploads(){
        return TopicBuilder.name("video_uploads").build();
    }

    @Bean
    public NewTopic genre_updates(){
        return TopicBuilder.name("genre_updates").build();
    }



}
