package com.example.VideoStreamingPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.VideoStreamingPlatform.entity.Likes;
import com.example.VideoStreamingPlatform.producer.Producer;
import com.example.VideoStreamingPlatform.service.LikeService;

@RestController
@RequestMapping("/api")
public class LikeController {
  @Autowired
  LikeService ls;
  @Autowired
  Producer p;

  @PostMapping("videos/{video_id}/user/{user_id}/like")
  public ResponseEntity<Likes> createLike(@PathVariable int video_id, @PathVariable int user_id,
      @RequestBody Likes likeObj) {
    Likes newLike = ls.createLike(video_id, user_id, likeObj);
    String likedBy = ls.getLikedBy(user_id);
    String videoTitle = ls.getVideoTitle(video_id);
    String response = likedBy + " has just liked " + videoTitle;
    p.sendLikeDataToTopic(response);
    return new ResponseEntity<Likes>(newLike, HttpStatus.CREATED);
  }
}
