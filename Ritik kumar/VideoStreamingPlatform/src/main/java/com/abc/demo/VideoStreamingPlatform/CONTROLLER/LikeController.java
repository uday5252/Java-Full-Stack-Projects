package com.abc.demo.VideoStreamingPlatform.CONTROLLER;

import com.abc.demo.VideoStreamingPlatform.ENTITY.LikeEntity;
import com.abc.demo.VideoStreamingPlatform.SERVICE.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    @Autowired
    LikeService ls;

    @GetMapping("/api/like/user/{userId}/video/{videoId}")
    public ResponseEntity<String> addLike(@PathVariable("userId") int uId,@PathVariable("videoId") int vId)
    {
        LikeEntity l=new LikeEntity();
        l.setUserId(uId);
        l.setVideoId(vId);
        ls.addLikeToVideo(l);
        return new ResponseEntity<>("Like added Successfully", HttpStatus.CREATED);
    }
}
