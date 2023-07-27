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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@Tag(name = "Video Controller", description = "This controller controls all the endpoints associated with the Video functions related to the application")

@RestController
public class VideoController {

    @Autowired
    VideoService service;

    @Autowired
    ResourceLoader loader;

    @Operation(summary = "Create Video", description = "This endpoint will take the Video details and adds it to the Database.", responses = {
            @ApiResponse(responseCode = "201", description = "This response code corresponds to the sucessful insertion of data into the database."),
            @ApiResponse(responseCode = "400", description = "This response code is sent when there is an internal error or there is a problem inserting into the database.")

    })
    @PostMapping("/api/admin/videos")
    public ResponseEntity<String> uploadVideo(@RequestBody VideoRequestDto inVideoRequestDto) {
        try {
            service.uploadVideo(inVideoRequestDto);
            return new ResponseEntity<String>("Video Uploaded Sucessfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get Video details", description = "This endpoint will fetch all Video details of the Video ID provided in the URL", responses = {
            @ApiResponse(responseCode = "200", description = "This response code corresponds to the sucessful fetching of the video details from the database. "),
            @ApiResponse(responseCode = "400", description = "This response code is sent when there is an internal error or there is a problem fetching the database. This may be caused if the video with the given VIDEO ID is not present in the database.")

    })
    @GetMapping("/api/videos/{videoId}")
    public ResponseEntity<?> getOneVideo(@PathVariable("videoId") int videoId) {
        try {
            VideoResponseDto vResponseDto = service.getOneVideo(videoId);
            return new ResponseEntity<VideoResponseDto>(vResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get Video details", description = "This endpoint will fetch all Video details of the Video ID provided in the URL and returns the video as a video Stream", responses = {
            @ApiResponse(responseCode = "200", description = "This response code corresponds to the sucessful fetching of the video details from the database. If there is a problem it responds with a null value")

    })
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

    @Operation(summary = "Get All Video details", description = "This endpoint will fetch all the Video details", responses = {
            @ApiResponse(responseCode = "200", description = "This response code corresponds to the sucessful fetching of all the videos from the database. The response can be null if there are no genres in the application database")
    })
    @GetMapping("/api/videos")
    public ResponseEntity<List<VideoResponseDto>> getAllVideos() {
        List<VideoResponseDto> videoResponseDtos = service.getAllVideos();
        return new ResponseEntity<List<VideoResponseDto>>(videoResponseDtos, HttpStatus.OK);
    }

    @Operation(summary = "Get All Video details of a specific Genre", description = "This endpoint will fetch all the Video details corresponding to the Genre given in the URL", responses = {
            @ApiResponse(responseCode = "200", description = "This response code corresponds to the sucessful fetching of all the videos from the database. The response can be null if there are no genres in the application database")
    })
    @GetMapping("/api/genres/{genreId}/videos")
    public ResponseEntity<List<VideoResponseDto>> getAllVideosFromGenre(@PathVariable("genreId") int genreId) {
        List<VideoResponseDto> videoResponseDtos = service.getVideosFromGenre(genreId);
        return new ResponseEntity<List<VideoResponseDto>>(videoResponseDtos, HttpStatus.OK);
    }

    @Operation(summary = "Update Video Details", description = "This endpoint will update the Video details from the Database.Please provide only the details to be updated along with the video id in the path.", responses = {
            @ApiResponse(responseCode = "201", description = "This response code corresponds to the sucessful update of data into the database."),
            @ApiResponse(responseCode = "400", description = "This response code is sent when there is an internal error or there is a problem updating the database.")

    })
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

    @Operation(summary = "Delete Video details", description = "This endpoint will take the VIDEO ID and deletes the Genre from the database", responses = {
            @ApiResponse(responseCode = "200", description = "This response code corresponds to the sucessful deletion of data from the application database."),
            @ApiResponse(responseCode = "400", description = "This response code is sent when there is an exception and the user with the given VIDEO ID is not found in the application")

    })
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
