package com.abc.demo.videostreaming.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.videostreaming.Entity.LikeEntity;

@Service
public class LikeService {

    @Autowired
    KafkaTemplate<String,String> kt;

    
    public void likeNotify(LikeEntity likeEntity) {
        kt.send("user_likes",likeEntity.toString());
    }
}
