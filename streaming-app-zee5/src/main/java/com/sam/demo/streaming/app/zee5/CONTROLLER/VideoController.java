package com.sam.demo.streaming.app.zee5.CONTROLLER;

import com.sam.demo.streaming.app.zee5.ENTITY.VideoEntity;
import com.sam.demo.streaming.app.zee5.PRODUCER.KafkaProducer;
import com.sam.demo.streaming.app.zee5.SERVICE.LikeService;
import com.sam.demo.streaming.app.zee5.SERVICE.VideoService;
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

    @PostMapping("/api/admin/videos/genre/{gid}/user/{uid}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VideoEntity> addVideo(@RequestBody VideoEntity ve, @PathVariable("gid") int genreId, @PathVariable("uid") int videoId){
       VideoEntity newvideo= vs.uploadvideo(ve,genreId,videoId);
        String user = ls.likedBy(videoId);
        String response = user + " has uploaded a new video ";
        kafkaProducer.sendDataofVideoUploads(response);
        return new ResponseEntity<VideoEntity>(newvideo,HttpStatus.CREATED);


    }

    @GetMapping("/api/video/{videoId}")
    public ResponseEntity<VideoEntity> getVideoById(@PathVariable("videoId") int vid){
        VideoEntity ve=vs.getVideoById(vid);
        return  new ResponseEntity<>(ve,HttpStatus.OK);
    }
    @GetMapping("/api/videos")
    public ResponseEntity<List<VideoEntity>> getAllVideos( ){
        List<VideoEntity> ve=vs.getAllVideo();
        return  new ResponseEntity<>(ve,HttpStatus.OK);
    }

    @GetMapping("/api/genres/{genreId}/videos")
    public ResponseEntity<List<VideoEntity>>  sameGenreVideo(@PathVariable("genreId") int gid){
        List<VideoEntity> ve=vs.getVideoByGenre(gid);
        return  new ResponseEntity<>(ve,HttpStatus.OK);
    }

    @PutMapping("/api/admin/videos/{videoId}")
    public void  updateVideo(@RequestBody VideoEntity ve,@PathVariable("videoId") int vid){
        vs.updateVideoDetails(ve,vid);

    }

    @DeleteMapping("/api/admin/videos/{videoId}")
    public ResponseEntity<String> deleteVideo(@PathVariable("videoId") int id){
        vs.deleteVideoById(id);
        return  new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);


    }












}
