package com.sivasai.endGame.SpringFinalProject.TopicsNames;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicNames {
    private final String user_likes = "user_likes";
    private final String user_comments = "user_comments";
    private final String video_uploads = "video_uploads";
    private final String genre_updates = "genre_updates";

    public String getUser_likes() {
        return user_likes;
    }

    public String getUser_comments() {
        return user_comments;
    }

    public String getVideo_uploads() {
        return video_uploads;
    }

    public String getGenre_updates() {
        return genre_updates;
    }
}
