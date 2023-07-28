package com.sivasai.endGame.SpringFinalProject.Controller;

import com.sivasai.endGame.SpringFinalProject.Entity.Video;
import com.sivasai.endGame.SpringFinalProject.ResponseGenerator.ResponseHandler;
import com.sivasai.endGame.SpringFinalProject.Service.VideoService;
import com.sivasai.endGame.SpringFinalProject.Service.VideoStreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    VideoService videoService;

    @Autowired
    VideoStreamingService videoStreamingService;

    @GetMapping("/{videoId}")
    public ResponseEntity<?> getVideoById(@PathVariable("videoId")int videoId){
        if(videoService.ifExists(videoId)) {
            Video video = videoService.getVideoById(videoId);
            return ResponseHandler.generateResponse("Video found by given Id", HttpStatus.FOUND, video.toString());
        }
        else {
            return ResponseHandler.generateResponse("Video not found please enter correct Id", HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping("/Movie-{Title}")
    public ResponseEntity<?> getVideoByName(@PathVariable("Title")String videoName){
        if(videoService.ifExistsByTitle(videoName)) {
            Video video = videoService.getVideoByName(videoName);
            return ResponseHandler.generateResponse("Video found by given Id", HttpStatus.FOUND, video.toString());
        }
        else {
            return ResponseHandler.generateResponse("Video not found please enter correct title", HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllVideos(){
        List<String> videoList = videoService.getAllVideos().stream().map(obj -> obj.toString()).collect(Collectors.toList());
        return ResponseHandler.generateResponse("Here is the list of all videos", HttpStatus.OK, videoList);
    }

    @GetMapping(value = "/play/video/{videoId}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable("videoId")int videoId){
        if(videoService.ifExists(videoId)){
            Video video = videoService.getVideoById(videoId);
            String url = video.getUrl();
            return videoStreamingService.getVideo(url);
        }
        else {
            return videoStreamingService.getVideo("random string: ajgbowbUORGORWEGUWI");
        }
    }
}
