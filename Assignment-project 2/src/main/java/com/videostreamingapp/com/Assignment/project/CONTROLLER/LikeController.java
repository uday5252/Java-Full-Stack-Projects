package com.videostreamingapp.com.Assignment.project.CONTROLLER;

import com.videostreamingapp.com.Assignment.project.ENTITY.LikeEntity;
import com.videostreamingapp.com.Assignment.project.PRODUCER.KafkaProducer;
import com.videostreamingapp.com.Assignment.project.SERVICE.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LikeController {
    @Autowired
    LikeService ls;

    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping("/user/{user_id}/video/{video_id}/like")
    public ResponseEntity<LikeEntity> createLike(@PathVariable int user_id, @PathVariable int video_id,
                                                 @RequestBody LikeEntity like){
       LikeEntity liked = ls.addlike(user_id,video_id,like);
       String user = ls.likedBy(user_id);
       String video = ls.likedVideo(video_id);
       String response = user + " liked video with title " + video;
       kafkaProducer.sendDataofLikedVideos(response);
       return new ResponseEntity<>(liked, HttpStatus.CREATED);

    }
    @DeleteMapping("/user/{user_id}/video/{video_id}/unlike")
    public ResponseEntity<String> unlikeVideo(@PathVariable int user_id, @PathVariable int video_id){
       ls.deleteLike(video_id,user_id);
       //return new ResponseEntity<>(unliked,HttpStatus.OK);
        return new ResponseEntity<>("disliked",HttpStatus.OK);

    }

}
