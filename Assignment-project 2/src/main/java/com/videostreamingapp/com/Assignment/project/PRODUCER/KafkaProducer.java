package com.videostreamingapp.com.Assignment.project.PRODUCER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public  void sendDataofLikedVideos(String Data){
        kafkaTemplate.send("User_Likes_Topic",Data);
    }

    public void sendDataofCommentedVideo(String Data){
        kafkaTemplate.send("User_Comments_Topic",Data);
    }

    public void sendDataofVideoUploads(String Data){
        kafkaTemplate.send("User_Videos_Topic",Data);
    }

    public void sendDataofGenreUploads(String Data){
        kafkaTemplate.send("User_Genre_Topic",Data);
    }

}
