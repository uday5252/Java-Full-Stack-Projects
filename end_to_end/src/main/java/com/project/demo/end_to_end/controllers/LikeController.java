package com.project.demo.end_to_end.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.demo.end_to_end.KafkaProducer;
import com.project.demo.end_to_end.entities.Likes;
import com.project.demo.end_to_end.service.LikeService;
import com.project.demo.end_to_end.service.UserService;
import com.project.demo.end_to_end.service.VideoService;

import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Like Controller",description = "This has endpoints to manipulate Likes")
@Controller
public class LikeController {
    @Autowired
    UserService us;
    @Autowired
    VideoService vs;
    @Autowired 
    LikeService ls;
    @Autowired
    KafkaProducer kp;

    @ResponseBody
    @GetMapping("/like/{videoId}/{user}")
    public String likeVideo(@PathVariable("videoId") int videoid,@PathVariable("user") String username){
        if(us.finduser(username) == null)    return "<a href = '/'>LogInToLike</a>";
        if(ls.findByUserAndVideo(username,videoid) == null){
            Likes l = new Likes();
            l.setLikedby(us.finduser(username));
            l.setVideo(vs.getVideo(videoid));
            ls.addLike(l);
            kp.send("user_likes", "user "+username+" liked video "+vs.getVideo(videoid).getTitle());
            return "Liked";
        }
        return "Already Liked Befroe";
    }
    @GetMapping("/unlike/{videoId}/{user}")
    @ResponseBody
    public String unlikeVideo(@PathVariable("videoId") int videoId, @PathVariable("user") String username){
        if(us.finduser(username) == null) return "<a href = '/login'>LogInToUnlike</a>";
        if(ls.findByUserAndVideo(username, videoId) != null){
            ls.delete(ls.findByUserAndVideo(username, videoId));
            kp.send("user_likes", "user "+username+" Unliked video "+vs.getVideo(videoId).getTitle());
            return "Unlike Successful";
        }
        return "This video is not liked by you. Unlike Unsuccessful";
    }
}
