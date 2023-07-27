package com.videostreamingapp.com.Assignment.project.CONTROLLER;

import com.videostreamingapp.com.Assignment.project.ENTITY.GenreEntity;
import com.videostreamingapp.com.Assignment.project.ENTITY.UserEntity;
import com.videostreamingapp.com.Assignment.project.ENTITY.VideoEntity;
import com.videostreamingapp.com.Assignment.project.PRODUCER.KafkaProducer;
import com.videostreamingapp.com.Assignment.project.SERVICE.LikeService;
import com.videostreamingapp.com.Assignment.project.SERVICE.UserService;
import com.videostreamingapp.com.Assignment.project.SERVICE.VideoService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VideoController {
    @Autowired
    VideoService vs;
    @Autowired
    KafkaProducer kafkaProducer;
    @Autowired
    LikeService ls;

    @PostMapping("/user/{user_id}/genre/{genre_id}/video/create")
    public ResponseEntity<VideoEntity> addVideo(@PathVariable int user_id, @PathVariable int  genre_id, @RequestBody VideoEntity video){
        VideoEntity newvideo = vs.uploadVideo(user_id,genre_id,video);
        String user = ls.likedBy(user_id);
        String response = user + " has uploaded a new video ";
        kafkaProducer.sendDataofVideoUploads(response);
        return new ResponseEntity<VideoEntity>(newvideo,HttpStatus.CREATED);

    }
    @GetMapping("/show/videos")
    public ResponseEntity<List<VideoEntity>>allVideo(){
        List<VideoEntity> allvideo = vs.allVideo();
        return new ResponseEntity<>(allvideo,HttpStatus.OK);
    }
    @GetMapping("/videos/{video_id}")
    public ResponseEntity<VideoEntity> getVideo(@PathVariable int video_id){
        VideoEntity covideo = vs.getVideo(video_id);
        return new ResponseEntity<>(covideo,HttpStatus.OK);
    }
    @GetMapping("/genre/{genre_id}/videos")
    public  ResponseEntity<List<VideoEntity>>getVideosByGenre(@PathVariable int genre_id){
        List<VideoEntity> res = vs.getVideoByGenre(genre_id);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @PutMapping("/videos/{video_id}/update")
    public void updateOneVideo(@RequestBody VideoEntity vobj,@PathVariable int video_id){

        vs.updateVideoDetails(vobj,video_id);
    }
    @DeleteMapping("/videos/{video_id}/delete")
    public void deleteOneVideo(@PathVariable int video_id){

        vs.deleteVideo(video_id);
    }






}
