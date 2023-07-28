package com.example.demo.controller;


import com.example.demo.entity.Video;
import com.example.demo.service.VideoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Video Controller", description = "This controller controls all the endpoints associated with the Video functions related to the application")
@RestController
public class VideoController {

    @Autowired
    VideoService videoService;

    @Autowired
    ResourceLoader resourceLoader;

    @PostMapping("/api/admin/video/upload")
    public String uploadVideo(@RequestBody Video video){
        videoService.collectVideoDetails(video);
        return "Video Uploaded Successfully";
    }

    @GetMapping(value = "/api/videos/{vid}")
    public ResponseEntity<Video> getVideo(@PathVariable int vid){
        Video videoDetails = videoService.retrieveVideoDetails(vid);
        return ResponseEntity.ok().body(videoDetails);

    }

    @GetMapping("/api/videos")
    public ResponseEntity<List<Video>> getAllVideos(){
        List<Video> videos = videoService.retrieveAllVideoDetails();
        return ResponseEntity.ok().body( videos);
    }

    @PutMapping("/api/admin/videos/{vid}")
    public String updateVideo(@PathVariable int vid,@RequestBody Video video ){
        video.setVid(vid);
        videoService.collectVideoDetails(video);
        return "Video Updated Successfully";
    }

    @DeleteMapping("/api/admin/video/{vid}")
    public String deleteVideo(@PathVariable int vid){
        videoService.deleteVideoById(vid);
        return  "Video Deleted successfully";
    }

  //  @GetMapping("/api/videoStreeaming/{vid}")
  @GetMapping(value = "/api/videoStreaming/{vid}",produces = "video/mp4")
  public Resource getVideoStreaming(@PathVariable int vid){
      Video videoDetails = videoService.retrieveVideoDetails(vid);
      return resourceLoader.getResource(videoDetails.getUrl());

  }


}
