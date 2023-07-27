package com.abc.demo.Zee5.Apache.Kafka.Producer.CONTROLLER;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.CommentEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.PRODUCER.KafkaProducer;
import com.abc.demo.Zee5.Apache.Kafka.Producer.SERVICE.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping("/comment/user/{user_id}/video/{video_id}")
    public ResponseEntity<CommentEntity> addComment(@PathVariable("user_id") int user_id, @PathVariable("video_id") int video_id, @RequestBody CommentEntity comment){
        CommentEntity comments = commentService.addComment(user_id, video_id, comment);
        String user = commentService.commentedBy(user_id);
        String video = commentService.commentedVideo(video_id);
        String response = user + " has commented on the video with title " + video;
        kafkaProducer.sendDataOfCommentedVideo(response);
        return new ResponseEntity<CommentEntity>(comments, HttpStatus.CREATED);
    }
}
