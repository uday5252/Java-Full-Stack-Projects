package com.abc.demo.VideoStreamingPlatform.KafkaTopic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Topics {

    @Bean
    public NewTopic likeTopic()
    {
        return TopicBuilder.name("likeTopic").partitions(100).build();
    }

    @Bean
    public NewTopic commentTopic()
    {
        return TopicBuilder.name("commentTopic").partitions(100).build();
    }

    @Bean
    public NewTopic videoTopic()
    {
        return TopicBuilder.name("videoTopic").partitions(100).build();
    }

    @Bean
    public NewTopic genreTopic()
    {
        return TopicBuilder.name("genreTopic").partitions(100).build();
    }
}
