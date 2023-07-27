package com.zee.org.zee5_Clone.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic
{
    @Bean
    public NewTopic createTopic1(){
        return TopicBuilder.name("user_likes").build();
    }
    @Bean
    public NewTopic createTopic2(){
        return TopicBuilder.name("user_comments").build();
    }
    @Bean
    public NewTopic createTopic3(){
        return TopicBuilder.name("video_uploads").build();
    }
    @Bean
    public NewTopic createTopic4(){
        return TopicBuilder.name("genre_updates").build();
    }



}
//        1. `user_likes`
//        2. `user_comments`
//        3. `video_uploads`
//        4. `genre_updates`
