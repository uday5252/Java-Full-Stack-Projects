package com.abc.demo.Zee5.Apache.Kafka.Producer.TOPIC;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic FirstTopic(){
        return TopicBuilder.name("User_Likes_Topic").build();
    }

    @Bean
    public NewTopic SecondTopic(){
        return TopicBuilder.name("User_Comments_Topic").build();
    }

    @Bean
    public NewTopic ThirdTopic(){
        return TopicBuilder.name("Video_Uploads_Topic").build();
    }

    @Bean
    public NewTopic FourthTopic(){
        return TopicBuilder.name("Genre_Updates_Topic").build();
    }
}
