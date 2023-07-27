package com.example.training.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.training.Project.Entities.LikeEntity;
import com.example.training.Project.Services.LikeService;

@RestController
public class LikeController {
    
    @Autowired
    LikeService lService;

    @PostMapping("/api/{uid}/{vid}/like")
    public ResponseEntity<String> setLike(@PathVariable("uid")int uid, @PathVariable("vid")int vid, @RequestBody LikeEntity like){
        lService.setLike(like,uid,vid);
        return new ResponseEntity<>("Like success!",HttpStatus.OK);
    }

    @GetMapping("/api/{uid}/{vid}/dislike")
    public ResponseEntity<String>disLike(@PathVariable("uid") int uid,@PathVariable("vid") int vid){
        lService.disLike(uid,vid);
        return new ResponseEntity<>("Unlike success!",HttpStatus.OK);
    }
}
