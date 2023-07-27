package com.zee.phani.project.ott.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AppConfig {

    @Bean
    ModelMapper createMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

    @Bean
    NewTopic userLikesTopic() {
        return TopicBuilder.name("user_likes").build();
    }

    @Bean
    NewTopic userCommentsTopic() {
        return TopicBuilder.name("user_comments").build();
    }

    @Bean
    NewTopic videoUploadsTopic() {
        return TopicBuilder.name("video_uploads").build();
    }

    @Bean
    NewTopic genreUpdatesTopic() {
        return TopicBuilder.name("genre_updates").build();
    }

}
