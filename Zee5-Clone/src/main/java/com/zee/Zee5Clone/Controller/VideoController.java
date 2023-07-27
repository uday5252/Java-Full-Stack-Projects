package com.zee.Zee5Clone.Controller;

import com.zee.Zee5Clone.Entity.Video;
import com.zee.Zee5Clone.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VideoController {
    @Autowired
    VideoService metadataService;
    @PostMapping("/admin/videos")
    public ResponseEntity<String> addVideo(@RequestBody Video video){
        //System.out.println(video.getId()+video.getGenreId()+video.getDescription()+video.getTitle()+video.getUrl()+video.getUploadedBy());
        metadataService.uploadVideo(video);
        return new ResponseEntity<>("video Successfully added", HttpStatus.OK);
    }

    @GetMapping("/videos/{videoId}")
    public ResponseEntity<Video> getVideo(@PathVariable("videoId") int videoId){
        Video video =metadataService.getVideo(videoId);
        return new ResponseEntity<>(video,HttpStatus.OK);
    }

    @GetMapping("/videos")
    public ResponseEntity<List<Video>> getVideo(){
        List<Video> videos = metadataService.getVideo();
        return new ResponseEntity<>(videos,HttpStatus.OK);
    }

    @GetMapping("/genres/{genreId}/videos")
    public ResponseEntity<List<Video>> getGenreVideo(@PathVariable("genreId") int genreCode){
        List<Video> videos = metadataService.getVideoByGenre(genreCode);
        return new ResponseEntity<>(videos,HttpStatus.OK);
    }

    @PutMapping("/admin/videos/{videoId}")
    public ResponseEntity<List<Video>> updateVideo(@RequestBody Video video,@PathVariable("videoId") int videoId){

        List<Video> videos = metadataService.updateVideo(video,videoId);
        return new ResponseEntity<>(videos,HttpStatus.OK);
    }

    @DeleteMapping("/admin/videos/{videoId}")
    public ResponseEntity<String> deleteVideo(@PathVariable("videoId") int videoId){
        metadataService.deleteVideo(videoId);
        return new ResponseEntity<>("successfully deleted",HttpStatus.OK);
    }
}
