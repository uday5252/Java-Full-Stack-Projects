package com.xyz.demo.EndProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.demo.EndProject.Entity.Like;
import com.xyz.demo.EndProject.Service.LikeService;

@RestController
public class LikeController {
    @Autowired
    LikeService ls;

    @PostMapping("/api/user/like/{videoId}/{userId}")
    public ResponseEntity<String> like(@PathVariable("videoId") int videoId, @PathVariable("userId") int userId) {
        String s = ls.createLike(videoId, userId);
        return new ResponseEntity<>(s, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/user/dislike/{videoId}/{userId}")
    public ResponseEntity<String> deletelike(@PathVariable("videoId") int videoId, @PathVariable("userId") int userId) {
        String s = ls.deleteLike(videoId, userId);
        return new ResponseEntity<>(s, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/user/like/all")
    public ResponseEntity<List<Like>> getAllLikes() {
        List<Like> likes = ls.getAllLikes();
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }
}

	