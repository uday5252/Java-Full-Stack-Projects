package com.abc.demo.Zee5.Apache.Kafka.Producer.PRODUCER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void sendDataOfLikedVideo(String data){
        kafkaTemplate.send("User_Likes_Topic", data);
    }

    public void sendDataOfCommentedVideo(String data){
        kafkaTemplate.send("User_Comments_Topic", data);
    }

    public void sendDataOfVideoUploads(String data){
        kafkaTemplate.send("Video_Uploads_Topic", data);
    }

    public void sendDataOfGenreUpdates(String data){
        kafkaTemplate.send("Genre_Updates_Topic", data);
    }
}
