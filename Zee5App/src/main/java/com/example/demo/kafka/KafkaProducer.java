package com.example.demo.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    KafkaTemplate<Integer,String> kt;

    public  void sendToTopic(String topic,String message){
         kt.send(topic,message);
    }
}
