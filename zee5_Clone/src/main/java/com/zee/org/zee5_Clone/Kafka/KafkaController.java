package com.zee.org.zee5_Clone.Kafka;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class KafkaController
{
    @Autowired
    KafkaProducer Kp;

    @GetMapping("/topic/addurl")
    public ResponseEntity<String> SendUrlAndUsernameToTopic(int videoid){
        String s=Kp.sendUserDataToTopic(videoid);
        return new ResponseEntity<>("Data is sent to topic", HttpStatus.OK);
    }
}
