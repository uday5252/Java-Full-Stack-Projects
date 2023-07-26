package com.abc.demo.VideoStreamingPlatform.CONTROLLER;

import com.abc.demo.VideoStreamingPlatform.ENTITY.VideoEntity;
import com.abc.demo.VideoStreamingPlatform.SERVICE.VideoService;
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

    @PostMapping("/api/admin/videos/{genreId}")
    public ResponseEntity<String> addNewVideo(@RequestBody VideoEntity video,@PathVariable("genreId") int gId)
    {
        vs.addVideo(video,gId);
        return new ResponseEntity<>("new video is uploaded", HttpStatus.CREATED);
    }

    @GetMapping("/api/videos/{videoId}")
    public ResponseEntity<VideoEntity> getVideoDetails(@PathVariable("videoId") int vId)
    {
        return new ResponseEntity<>(vs.getVideo(vId),HttpStatus.OK);
    }

    @GetMapping("/api/videos")
    public ResponseEntity<List<VideoEntity>> getAllVideos()
    {
        return new ResponseEntity<>(vs.allVideo(),HttpStatus.OK);
    }

    @GetMapping("/api/genres/{genreId}/videos")
    public ResponseEntity<List<VideoEntity>> VideosByGenreId(@PathVariable("genreId") int gId)
    {
        return new ResponseEntity<>(vs.getVideoByGenre(gId),HttpStatus.OK);
    }

    @PutMapping("/api/admin/videos/{videoId}")
    public ResponseEntity<String> updateDetails(@RequestBody VideoEntity ve,@PathVariable("videoId") int vId)
    {
        vs.updateVideoDetails(ve,vId);
        return new ResponseEntity<>("video Details Successfully Updated",HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/videos/{videoId}")
    public ResponseEntity<String> deleteVideoById(@PathVariable("videoId") int vId)
    {
        vs.deleteVideo(vs.getVideo(vId));
        return new ResponseEntity<>("video deleted Successfully",HttpStatus.OK);
    }
}
