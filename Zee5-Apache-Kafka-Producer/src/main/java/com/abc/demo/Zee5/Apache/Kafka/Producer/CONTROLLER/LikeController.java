package com.abc.demo.Zee5.Apache.Kafka.Producer.CONTROLLER;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.LikeEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.PRODUCER.KafkaProducer;
import com.abc.demo.Zee5.Apache.Kafka.Producer.SERVICE.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    @Autowired
    LikeService likeService;

    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping("/like/user/{user_id}/video/{video_id}")
    public ResponseEntity<String> addLike(@PathVariable("user_id") int user_id, @PathVariable("video_id") int video_id, LikeEntity like){
         likeService.addLike(user_id, video_id, like);
         String user = likeService.likedBy(user_id);
         String video = likeService.likedVideo(video_id);
         String response = user + " like video with title " + video;
         kafkaProducer.sendDataOfLikedVideo(response);
        return new ResponseEntity<String>("Like Created", HttpStatus.CREATED);
    }
}
