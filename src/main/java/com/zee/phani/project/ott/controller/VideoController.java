package com.zee.phani.project.ott.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zee.phani.project.ott.dto.VideoRequestDto;
import com.zee.phani.project.ott.dto.VideoResponseDto;
import com.zee.phani.project.ott.service.VideoService;

import reactor.core.publisher.Mono;

@RestController
public class VideoController {

    @Autowired
    VideoService service;

    @Autowired
    ResourceLoader loader;

    @PostMapping("/api/admin/videos")
    public ResponseEntity<String> uploadVideo(@RequestBody VideoRequestDto inVideoRequestDto) {
        try {
            service.uploadVideo(inVideoRequestDto);
            return new ResponseEntity<String>("Video Uploaded Sucessfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/videos/{videoId}")
    public ResponseEntity<?> getOneVideo(@PathVariable("videoId") int videoId) {
        try {
            VideoResponseDto vResponseDto = service.getOneVideo(videoId);
            return new ResponseEntity<VideoResponseDto>(vResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/api/videos/{videoId}/play", produces = "video/mp4")
    public Mono<Resource> getOneVideoAndPlay(@PathVariable("videoId") int videoId) {
        try {
            VideoResponseDto vResponseDto = service.getOneVideo(videoId);
            return Mono.fromSupplier(
                    () -> loader.getResource(vResponseDto.getUrl()));
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/api/videos")
    public ResponseEntity<List<VideoResponseDto>> getAllVideos() {
        List<VideoResponseDto> videoResponseDtos = service.getAllVideos();
        return new ResponseEntity<List<VideoResponseDto>>(videoResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/api/genres/{genreId}/videos")
    public ResponseEntity<List<VideoResponseDto>> getAllVideosFromGenre(@PathVariable("genreId") int genreId) {
        List<VideoResponseDto> videoResponseDtos = service.getVideosFromGenre(genreId);
        return new ResponseEntity<List<VideoResponseDto>>(videoResponseDtos, HttpStatus.OK);
    }

    @PutMapping("/api/admin/videos/{videoId}")
    public ResponseEntity<String> updateVideo(@RequestBody VideoRequestDto inVideoRequestDto,
            @PathVariable("videoId") int videoId) {
        try {
            service.updateVideo(inVideoRequestDto, videoId);
            return new ResponseEntity<String>("Video Updated Sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/admin/videos/{videoId}")
    public ResponseEntity<String> deleteVideo(@PathVariable("videoId") int videoId) {
        try {
            service.deleteVideo(videoId);
            return new ResponseEntity<String>("Video Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
