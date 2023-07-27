package com.sivasai.endGame.SpringFinalProject.Producer;

import com.sivasai.endGame.SpringFinalProject.TopicsNames.KafkaTopicNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    KafkaTopicNames kafkaTopicNames;

    public void sendDataToVideoUploads(String message){
        kafkaTemplate.send(kafkaTopicNames.getVideo_uploads(), message);
    }

    public void sendDataToGenreUpdates(String message){
        kafkaTemplate.send(kafkaTopicNames.getGenre_updates(), message);
    }

    public void sendDataToUserLikes(String message){
        kafkaTemplate.send(kafkaTopicNames.getUser_likes(), message);
    }

    public void sendDataToUserComments(String message){
        kafkaTemplate.send(kafkaTopicNames.getUser_comments(), message);
    }
}
