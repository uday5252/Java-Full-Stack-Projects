package com.project.demo.end_to_end;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    KafkaTemplate<String,String> kt;
    public void send(String topic,String messege){
        kt.send(topic,messege);
    }
}
