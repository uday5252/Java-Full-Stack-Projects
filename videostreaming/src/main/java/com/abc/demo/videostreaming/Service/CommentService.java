package com.abc.demo.videostreaming.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.videostreaming.Entity.CommentEntity;

@Service
public class CommentService {
    @Autowired
    KafkaTemplate<String,String> kt;

    public void commentNotify(CommentEntity commentEntity) {
        kt.send("user_comments",commentEntity.toString());
    }
}
