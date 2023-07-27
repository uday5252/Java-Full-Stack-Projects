package com.example.training.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.training.Project.Entities.CommentEntity;
import com.example.training.Project.Services.CommentService;

@RestController
public class CommentController {
    
    @Autowired
    CommentService cService;

    @PostMapping("/api/{uid}/{vid}/comment")
    public ResponseEntity<String>setComment(@PathVariable("uid") int uid,@PathVariable("vid") int vid, @RequestBody CommentEntity comment){
        cService.setComment(comment, uid, vid);
        return new ResponseEntity<>("Comment set successfully",HttpStatus.OK);
    }

    @GetMapping("/api/{uid}/{vid}/uncomment")
    public ResponseEntity<String> uncomment(@PathVariable("uid")int uid, @PathVariable("vid")int vid){
        cService.unComment(vid,uid);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
