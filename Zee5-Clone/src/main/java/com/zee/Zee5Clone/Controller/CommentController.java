package com.zee.Zee5Clone.Controller;

import com.zee.Zee5Clone.Entity.CommentEntity;
import com.zee.Zee5Clone.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;
    @PostMapping("/video/{videoId}/{userId}/comment")
    public ResponseEntity<String> addComment(@RequestBody CommentEntity comment, @PathVariable("videoId") int videoId, @PathVariable("userId")
                           int userId){
        commentService.addComment(comment,userId,videoId);
        return new ResponseEntity<>("comment sucessfully added", HttpStatus.OK);
    }
}
