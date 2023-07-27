package com.videostreamingapp.com.Assignment.project.CONTROLLER;

import com.videostreamingapp.com.Assignment.project.ENTITY.CommentEntity;
import com.videostreamingapp.com.Assignment.project.PRODUCER.KafkaProducer;
import com.videostreamingapp.com.Assignment.project.SERVICE.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CommentController {
    @Autowired
    CommentService cs;
    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping("/user/{user_id}/video/{video_id}/comment")
    public ResponseEntity<CommentEntity> addComment(@PathVariable int user_id, @PathVariable int video_id, @RequestBody CommentEntity comment){
        CommentEntity commented = cs.addComment(user_id,video_id,comment);
        String user = cs.commentedBy(user_id);
        String video = cs.commentedVideo(video_id);
        String response = user + " has commented on the video titled " + video;
        kafkaProducer.sendDataofCommentedVideo(response);
        return  new ResponseEntity<>(commented, HttpStatus.CREATED);
    }
    @DeleteMapping("/user/{user_id}/video/{video_id}/uncomment")
    public ResponseEntity<CommentEntity> deleteComment(@PathVariable int user_id, @PathVariable int video_id){
        CommentEntity uncomment = cs.deleteComment(video_id,user_id);
        return new ResponseEntity<>(uncomment,HttpStatus.OK);
    }




}
