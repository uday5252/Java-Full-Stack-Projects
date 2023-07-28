package com.example.demo.controller;


import com.example.demo.entity.Comment;
import com.example.demo.entity.LikeEntity;
import com.example.demo.service.CommentService;
import com.example.demo.service.LikeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Comment Controller", description = "This controller controls all the endpoints associated with the COMMENT functions related to the application")

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/api/videos/comment")
    public String likeTheVideo(@RequestBody Comment comment){
        commentService.collectCommentDetails(comment);
        return "Comment the video";
    }
}
