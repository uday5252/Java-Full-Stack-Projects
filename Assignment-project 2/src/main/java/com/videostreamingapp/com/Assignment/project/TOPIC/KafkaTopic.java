package com.videostreamingapp.com.Assignment.project.TOPIC;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
    @Bean
    public NewTopic firstTopic(){
    return TopicBuilder.name("User_Likes_Topic").build();
    }
    @Bean
    public NewTopic secondTopic(){
        return TopicBuilder.name("User_Comments_Topic").build();
    }
    @Bean
    public NewTopic thirdTopic(){
        return TopicBuilder.name("User_Videos_Topic").build();
    }
    @Bean
    public NewTopic fourthTopic(){
        return TopicBuilder.name("User_Genre_Topic").build();
    }
}
