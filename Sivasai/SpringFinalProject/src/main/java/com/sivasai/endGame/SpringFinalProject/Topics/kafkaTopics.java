package com.sivasai.endGame.SpringFinalProject.Topics;

import com.sivasai.endGame.SpringFinalProject.TopicsNames.KafkaTopicNames;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafkaTopics {

    @Autowired
    KafkaTopicNames kafkaTopicNames;

    @Bean
    public NewTopic userLikesTopic(){
        return TopicBuilder.name(kafkaTopicNames.getUser_likes()).build();
    }

    @Bean
    public NewTopic userCommentsTopic(){
        return TopicBuilder.name(kafkaTopicNames.getUser_comments()).build();
    }

    @Bean
    public NewTopic videoUploadsTopic(){
        return TopicBuilder.name(kafkaTopicNames.getVideo_uploads()).build();
    }

    @Bean
    public NewTopic genreUpdatesTopic(){
        return TopicBuilder.name(kafkaTopicNames.getGenre_updates()).build();
    }
}
