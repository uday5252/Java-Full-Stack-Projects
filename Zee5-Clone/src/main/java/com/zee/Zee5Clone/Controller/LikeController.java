package com.zee.Zee5Clone.Controller;

import com.zee.Zee5Clone.Entity.LikeEntity;
import com.zee.Zee5Clone.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LikeController {

    @Autowired
    LikeService likeService;

    @GetMapping("/video/like/{videoId}/{userId}")
    public ResponseEntity<String>addLike(@PathVariable("videoId") int videoId, @PathVariable("userId") int userId){
        likeService.addLike(videoId,userId);
        return new ResponseEntity<>("like added successfully", HttpStatus.OK);
    }
@GetMapping("/likes")
    public List<LikeEntity> getLike(){
        List<LikeEntity> likes = likeService.getLike();
        return likes;
    }
}
