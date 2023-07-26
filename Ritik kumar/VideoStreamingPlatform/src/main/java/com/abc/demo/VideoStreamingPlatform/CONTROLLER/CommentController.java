package com.abc.demo.VideoStreamingPlatform.CONTROLLER;

import com.abc.demo.VideoStreamingPlatform.ENTITY.CommentEntity;
import com.abc.demo.VideoStreamingPlatform.SERVICE.CommentService;
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
    CommentService cs;

    @PostMapping("/api/comment/user/{userId}/video/{videoId}")
    public ResponseEntity<CommentEntity> addComment(@RequestBody CommentEntity comment,@PathVariable("userId") int uId,@PathVariable("videoId") int vId)
    {
        comment.setUserId(uId);
        comment.setVideoId(vId);
        cs.addCommentToVideo(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
}
