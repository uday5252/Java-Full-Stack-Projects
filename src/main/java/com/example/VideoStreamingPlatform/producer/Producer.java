package com.example.VideoStreamingPlatform.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
  @Autowired
  KafkaTemplate<Integer, String> kt;

  public void sendLikeDataToTopic(String message) {
    kt.send("user_likes", message);
  }

  public void sendCommentDataToTopic(String message) {
    kt.send("user_comments", message);
  }

  public void sendVideoUploadDataToTopic(String message) {
    kt.send("video_uploads", message);
  }

  public void sendGenreUpdateDataToTopic(String message) {
    kt.send("genre_updates", message);
  }

}
