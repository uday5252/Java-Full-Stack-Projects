package com.sam.demo.streaming.app.zee5.CONTROLLER;



import com.sam.demo.streaming.app.zee5.ENTITY.CommentEntity;
import com.sam.demo.streaming.app.zee5.PRODUCER.KafkaProducer;
import com.sam.demo.streaming.app.zee5.SERVICE.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    public class CommentController {

        @Autowired
        CommentService cs;
        @Autowired
        KafkaProducer kafkaProducer;

        @PostMapping("/user/{user_id}/video/{video_id}/comment")
        public ResponseEntity<CommentEntity> addComment(@PathVariable int user_id, @PathVariable int video_id, @RequestBody CommentEntity comment) {
            CommentEntity commented = cs.addComment(user_id, video_id, comment);
            String user = cs.commentedBy(user_id);
            String video = cs.commentedVideo(video_id);
            String response = user + " has commented on the video titled " + video;
            kafkaProducer.sendDataofCommentedVideo(response);
            return new ResponseEntity<>(commented, HttpStatus.CREATED);
        }
    }


