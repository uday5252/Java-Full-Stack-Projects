package com.zee.phani.project.ott.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class KafkaProducer {

    @Autowired
    KafkaTemplate<String, String> kt;

    public <T> void UploadToTopic(String topicName, T data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String dataString = mapper.writeValueAsString(data);
        kt.send(topicName, dataString);
    }
}
