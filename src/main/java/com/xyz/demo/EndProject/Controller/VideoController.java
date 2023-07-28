package com.xyz.demo.EndProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyz.demo.EndProject.Entity.VideoEntity;
import com.xyz.demo.EndProject.Service.VideoService;

@Controller
@RequestMapping("/api")
public class VideoController {
   
	@Autowired
	VideoService videoService;
	
	
	@PostMapping("/admin/videos/{uid}/{gid}")
    public ResponseEntity<String> uploadVideo(@RequestBody VideoEntity video,@PathVariable("uid")int userId,@PathVariable("gid")int genreId) 
	{
        videoService.uploadNewVideo(video,userId,genreId);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @GetMapping("/videos/{videoId}")
    public ResponseEntity<VideoEntity> getVideoDetails(@PathVariable int videoId) {
        VideoEntity video = videoService.getVideoById(videoId);
        if (video != null) {
            return ResponseEntity.ok(video);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/videos")
    public ResponseEntity<List<VideoEntity>> getAllVideos() {
        List<VideoEntity> videos = videoService.getAllVideos();
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/genres/{genreId}/videos")
    public String getVideosByGenre(@PathVariable("genreId") int genreId,Model m) {
        List<VideoEntity> videos = videoService.getVideosByGenre(genreId);
        m.addAttribute("videos",videos);
        return "genreVideos";
    }

    @PutMapping("/admin/videos/{videoId}")
    public ResponseEntity<VideoEntity> updateVideo(@PathVariable("videoId") int videoId, @RequestBody VideoEntity video) {
        VideoEntity updatedVideo = videoService.updateVideo(videoId, video);
        if (updatedVideo != null) {
            return ResponseEntity.ok(updatedVideo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/videos/{videoId}")
    public ResponseEntity<Void> deleteVideo(@PathVariable int videoId) {
        boolean deleted = videoService.deleteVideo(videoId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/stream/videos")
    public String getVideosStreaming(Model model)
    {
    	List<VideoEntity> videos=videoService.getAllVideo();
    	model.addAttribute("path",videos);
    	return "completeStreaming";
    }

}
