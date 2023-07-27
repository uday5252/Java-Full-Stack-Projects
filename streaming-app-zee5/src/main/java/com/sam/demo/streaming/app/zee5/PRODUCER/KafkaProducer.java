package com.sam.demo.streaming.app.zee5.PRODUCER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public  void sendDataofLikedVideos(String Data){
        kafkaTemplate.send("user_likes",Data);
    }

    public void sendDataofCommentedVideo(String Data){
        kafkaTemplate.send("user_comments",Data);
    }

    public void sendDataofVideoUploads(String Data){
        kafkaTemplate.send("video_uploads",Data);
    }

    public void sendDataofGenreUploads(String Data){
        kafkaTemplate.send("genre_updates",Data);
    }

}
