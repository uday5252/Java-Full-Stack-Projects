package com.example.training.Project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.training.Project.Entities.VideoEntity;
import com.example.training.Project.Services.VideoService;

import reactor.core.publisher.Mono;

@RestController
public class VideoController {
    
    @Autowired
    VideoService vService;

    @PostMapping("/api/admin/{uid}/videos")
    public ResponseEntity<String> createVideo(@RequestBody VideoEntity video,@PathVariable("uid") int uid){//, @PathVariable("gid") int gid){
        vService.createVideo(video,uid);//,gid);
        return new ResponseEntity<String>("Video created Successsfully!", HttpStatus.OK);
    }

    @GetMapping("/api/videos/{id}")
    public ResponseEntity<Object>oneVideoDeets(@PathVariable("id") int id){
        return new ResponseEntity<Object>(vService.oneVideoDetails(id), HttpStatus.OK);
    }

    @GetMapping("/api/videos")
    public ResponseEntity<List<Object>>allVideosDeets(){
        return new ResponseEntity<List<Object>>(vService.allVideos(), HttpStatus.OK);
    }

    @PutMapping(value="api/admin/videos/{id}")
    public ResponseEntity<String> updateVideo(@PathVariable("id") int id, @RequestBody VideoEntity video) {
        //TODO: process PUT request
        vService.updatevid(id,video);
        return new ResponseEntity<String>("Video details updated!", HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/videos/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable("id") int id){
        vService.deleteOneVideo(id);
        return new ResponseEntity<String>("Video details deleted!", HttpStatus.OK);
    }

    @GetMapping(value = "/stream/{video}",produces="video/mp4")
    public Mono<Resource> streamVideo(@PathVariable("video") String name){
        return vService.streamVideo(name);
    }
    
}
