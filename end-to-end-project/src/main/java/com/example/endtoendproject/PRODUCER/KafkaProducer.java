package com.example.endtoendproject.PRODUCER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
	@Autowired
    KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendData1(String link1){
	    kafkaTemplate.send("user_likes", link1);
	    }
	public void sendData2(String link2){
	    kafkaTemplate.send("user_comments", link2);
	    }
	public void sendData3(String link3){
	    kafkaTemplate.send("video_uploads", link3);
	    }
	public void sendData4(String link4){
	    kafkaTemplate.send("genre_updates", link4);
	    }
}
