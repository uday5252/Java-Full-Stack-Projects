package com.xyz.demo.EndProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.demo.EndProject.Entity.Comment;
import com.xyz.demo.EndProject.Service.CommentService;

@RestController
@RequestMapping("/api/video")
public class CommentController {
    @Autowired
    private CommentService cs;

    @PostMapping("/comment/{userId}/{videoId}")
    public ResponseEntity<String> addComment(@PathVariable("userId") int userId, @PathVariable("videoId") int videoId,@RequestBody Comment c) {
        String message = cs.addComment(userId, videoId,c);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") int commentId) {
        String message = cs.deleteComment(commentId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = cs.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable("commentId") int commentId, @RequestBody String newCommentText) {
        String message = cs.updateComment(commentId, newCommentText);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}


