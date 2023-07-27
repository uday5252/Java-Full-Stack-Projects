package com.abc.demo.Zee5.Apache.Kafka.Producer.CONTROLLER;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.VideoEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.PRODUCER.KafkaProducer;
import com.abc.demo.Zee5.Apache.Kafka.Producer.SERVICE.LikeService;
import com.abc.demo.Zee5.Apache.Kafka.Producer.SERVICE.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VideoController {

    @Autowired
    VideoService videoService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    LikeService likeService;

    @PostMapping("/video/add/genre/{genre_id}/user/{user_id}")
    public ResponseEntity<String> addVideo(@RequestBody VideoEntity video, @PathVariable("genre_id") int genre_id, @PathVariable("user_id") int user_id){
         videoService.addVideo(video, genre_id, user_id);
         String user = likeService.likedBy(user_id);
         String response = user + " has uploaded a new video.";
         kafkaProducer.sendDataOfVideoUploads(response);
        return new ResponseEntity<String>("Video Data Added", HttpStatus.CREATED);
    }

    @GetMapping("/videos")
    public ResponseEntity<List<VideoEntity>> getAllTheVideos(){
       List<VideoEntity> videos = videoService.getAllVideos();
       return new ResponseEntity<List<VideoEntity>>(videos, HttpStatus.OK);
    }

    @GetMapping("/video/{video_id}")
    public ResponseEntity<VideoEntity> getVideo(@PathVariable("video_id") int video_id){
        VideoEntity video = videoService.getVideo(video_id);
        return new ResponseEntity<VideoEntity>(video, HttpStatus.OK);
    }

    @GetMapping("/videos/genre/{genre_id}")
    public ResponseEntity<List<VideoEntity>> getVideoByGenre(@PathVariable("genre_id") int genre_id){
        List<VideoEntity> video = videoService.getVideosByGenre(genre_id);
        return new ResponseEntity<List<VideoEntity>>(video, HttpStatus.OK);
    }

    @PutMapping("/video/{video_id}/update")
    public ResponseEntity<String> updateDetailsOfVideo(@RequestBody VideoEntity video, @PathVariable("video_id") int video_id){
        videoService.updateVideoDetails(video, video_id);
        return new ResponseEntity<String>("Video Data Updated", HttpStatus.OK);
    }

    @DeleteMapping("/video/{video_id}/delete")
    public ResponseEntity<String> deleteVideo(@PathVariable("video_id") int video_id){
        videoService.deleteVideo(video_id);
        return new ResponseEntity<String>("Video Deleted", HttpStatus.OK);
    }


}
