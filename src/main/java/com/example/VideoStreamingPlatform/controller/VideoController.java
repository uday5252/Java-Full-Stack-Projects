package com.example.VideoStreamingPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.VideoStreamingPlatform.entity.Video;
import com.example.VideoStreamingPlatform.producer.Producer;
import com.example.VideoStreamingPlatform.service.VideoService;

@RestController
@RequestMapping("/api")
public class VideoController {
  @Autowired
  VideoService vs;
  @Autowired
  Producer p;

  @PostMapping("/user/{user_id}/genre/{genre_id}/video/create")
  public ResponseEntity<Video> createVideo(@PathVariable int user_id, @PathVariable int genre_id,
      @RequestBody Video videoObj) {
    Video newVideo = vs.createVideo(user_id, genre_id, videoObj);
    String uploader = vs.getUploader(user_id);
    String notification = uploader + " has just uploaded a new video.";
    p.sendVideoUploadDataToTopic(notification);
    return new ResponseEntity<Video>(newVideo, HttpStatus.CREATED);
  }

  @GetMapping("/videos")
  public ResponseEntity<List<Video>> getAllVideos() {
    List<Video> allVideos = vs.getAllVideos();
    return new ResponseEntity<List<Video>>(allVideos, HttpStatus.OK);
  }

  @GetMapping("/videos/{video_id}")
  public ResponseEntity<Video> getVideo(@PathVariable int video_id) {
    Video videoResponse = vs.getVideo(video_id);
    return new ResponseEntity<Video>(videoResponse, HttpStatus.OK);
  }

  @GetMapping("/genre/{genre_id}/videos")
  public ResponseEntity<List<Video>> getVideosByGenre(@PathVariable int genre_id) {
    List<Video> response = vs.getVideosByGenre(genre_id);
    return new ResponseEntity<List<Video>>(response, HttpStatus.OK);
  }

  @PutMapping("/videos/{video_id}/update")
  public ResponseEntity<Video> updateVideo(@PathVariable int video_id, @RequestBody Video videoObj) {
    Video response = vs.updateVideo(video_id, videoObj);
    return new ResponseEntity<Video>(response, HttpStatus.OK);
  }

  @DeleteMapping("/videos/{video_id}/delete")
  public ResponseEntity<String> deleteVideo(@PathVariable int video_id) {
    vs.deleteVideo(video_id);
    return new ResponseEntity<String>("video deleted succesfully.", HttpStatus.OK);
  }
}
