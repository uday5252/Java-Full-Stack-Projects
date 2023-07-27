package com.sam.demo.streaming.app.zee5.CONTROLLER;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sam.demo.streaming.app.zee5.ENTITY.LikeEntity;
import com.sam.demo.streaming.app.zee5.PRODUCER.KafkaProducer;
import com.sam.demo.streaming.app.zee5.SERVICE.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController{



    @Autowired
    KafkaProducer kafkaProducer;





    @Autowired
    LikeService ls;






    @PostMapping("/user/{user_id}/video/{video_id}/like")
    public ResponseEntity<LikeEntity> createLike(@PathVariable int user_id, @PathVariable int video_id,
                                                 @RequestBody LikeEntity like){
        LikeEntity liked = ls.addlike(user_id,video_id,like);
        String user = ls.likedBy(user_id);
        String video = ls.likedVideo(video_id);
        String response = user + "liked video with title " + video;
        kafkaProducer.sendDataofLikedVideos(response);
        return new ResponseEntity<>(liked, HttpStatus.CREATED);

    }


}
