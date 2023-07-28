package com.project.demo.end_to_end.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.demo.end_to_end.KafkaProducer;
import com.project.demo.end_to_end.entities.Comment;
import com.project.demo.end_to_end.service.CommentService;
import com.project.demo.end_to_end.service.UserService;
import com.project.demo.end_to_end.service.VideoService;

import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Commnet Controller", description = "This has endpoints to manipulate comments")
@Controller
public class CommentController {
    @Autowired
    CommentService cs;
    @Autowired
    UserService us;
    @Autowired
    VideoService vs;
    @Autowired
    KafkaProducer kp;
    
    @ResponseBody
    @PostMapping("/comment/{videoId}/{user}")
    public String addComment(@PathVariable("videoId") int videoId, @PathVariable("user") String username, @RequestParam("content") String content){
        if(us.finduser(username) == null) return "<a href = '/login'>LoginToAddComment</a>";
        if(cs.findbyuserandcomment(username,username+":- "+content) != null) return "You have already commented with same text. Try commenting with differnt string";
        Comment c = new Comment();
        c.setComment(username+":- "+content);
        c.setUser(us.finduser(username));
        c.setVideo(vs.getVideo(videoId));
        cs.addComment(c);
        kp.send("user_comments", "user "+username+" commented video "+vs.getVideo(videoId).getTitle()+" as :- "+content);
        return "Added Comment";
    }
}
