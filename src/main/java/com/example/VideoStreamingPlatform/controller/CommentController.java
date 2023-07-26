package com.example.VideoStreamingPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.VideoStreamingPlatform.entity.Comment;
import com.example.VideoStreamingPlatform.producer.Producer;
import com.example.VideoStreamingPlatform.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
  @Autowired
  CommentService cs;
  @Autowired
  Producer p;

  @PostMapping("videos/{video_id}/user/{user_id}/comment")
  public ResponseEntity<Comment> createComment(@PathVariable int video_id, @PathVariable int user_id,
      @RequestBody Comment commentObj) {
    Comment newComment = cs.createComment(video_id, user_id, commentObj);
    String commentor = cs.getCommentor(user_id);
    String videoTitle = cs.getVideoTitle(video_id);
    String response = commentor + " has just commented on the video: " + videoTitle;
    p.sendCommentDataToTopic(response);
    return new ResponseEntity<Comment>(newComment, HttpStatus.CREATED);
  }
}
