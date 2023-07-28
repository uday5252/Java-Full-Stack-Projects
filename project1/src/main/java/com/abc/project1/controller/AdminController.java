package com.abc.project1.controller;

import com.abc.project1.ApiResponse.ResponseHandler;
import com.abc.project1.ExceptionHandling.CustomException.InvalidIdException;
import com.abc.project1.ExceptionHandling.CustomException.ResourceNotFoundException;
import com.abc.project1.entity.Genre;
import com.abc.project1.entity.Video;
import com.abc.project1.service.CommonService;
import com.abc.project1.service.GenreService;
import com.abc.project1.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    GenreService gs;
    @Autowired
    VideoService vs;

    @Autowired
    CommonService commonService;

    @Autowired
    KafkaTemplate<String, Object> kt;

    @PostMapping("/genres")
    public ResponseEntity<Map<String, Object>> addNewGenre(@RequestBody Genre genre, HttpServletRequest request){
        Genre savedGenre = gs.addThisGenre(genre);
        kt.send("genre_updates", "genre notification", savedGenre + " added successfully.");
        return ResponseHandler.generateResponse(savedGenre, HttpStatus.CREATED, "new genre creation success.", request.getRequestURI());
    }

    @PostMapping("/videos")
    public ResponseEntity<Map<String, Object>> addNewVideo(@RequestBody Video video, @RequestParam("uploadedById") int uploadedById, @RequestParam("genresIds") Set<Integer> genresIds, HttpServletRequest request) throws InvalidIdException, ResourceNotFoundException {
        Video savedVideo = vs.addThisVideo(video, uploadedById, genresIds);
        kt.send("video_uploades", "video notification", savedVideo + " uploaded successfully.");
        return ResponseHandler.generateResponse(savedVideo, HttpStatus.CREATED, "new video uploaded success.", request.getRequestURI());
    }

    @PutMapping("/videos/{videoId}/genres")
    public ResponseEntity<Map<String, Object>> updateGenresOnVideo(@PathVariable("videoId") int videoId, @RequestParam("genresIds") Set<Integer> genresIds, HttpServletRequest request) throws InvalidIdException, ResourceNotFoundException {
        vs.updateGenresOnThisVideo(videoId, genresIds);
        return ResponseHandler.generateResponse(null, HttpStatus.OK, "genres updated on video with videoId "+videoId+".", request.getRequestURI());
    }

    @PutMapping("/genres/{genreId}")
    public ResponseEntity<Map<String, Object>> updateGenre(@RequestBody Genre genreDetailsToUpdate, @PathVariable("genreId") int genreId, HttpServletRequest request) throws InvalidIdException {
        Genre updated = gs.updateThisGenre(genreId, genreDetailsToUpdate);
        kt.send("genre_updates", "genre notification", updated + " updated successfully.");
        return ResponseHandler.generateResponse(updated, HttpStatus.OK, "genre updated with genreId "+genreId+".", request.getRequestURI());
    }

    @PutMapping("/videos/{videoId}")
    public ResponseEntity<Map<String, Object>> updateVideo(@RequestBody Video videoDetailsToUpdate, @PathVariable("videoId") int videoId, HttpServletRequest request) throws ResourceNotFoundException, InvalidIdException {
        Video updated = vs.updateThisVideo(videoId, videoDetailsToUpdate);
        kt.send("video_uploades", "video notification", updated + " updated successfully.");
        return ResponseHandler.generateResponse(updated, HttpStatus.OK, "video updated with videoId "+videoId+".", request.getRequestURI());
    }

    @DeleteMapping("/genres/{genreId}")
    public ResponseEntity<Map<String, Object>> deleteGenre(@PathVariable("genreId") int genreId, HttpServletRequest request) throws InvalidIdException, ResourceNotFoundException {
        // why direct delete is giving foreign key constraint error?
        // why not implicit removing of link with video?

        // anyway following is the solution, not efficient

        // remove link with video explicitly
        commonService.removeGenreLinkToVideos(genreId);
        // links removed

        gs.deleteThisGenre(genreId);
        return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "genre with genreId "+genreId+" deleted success.", request.getRequestURI());
    }

    @DeleteMapping("/videos/{videoId}")
    public ResponseEntity<Map<String, Object>> deleteVideo(@PathVariable("videoId") int videoId, HttpServletRequest request) throws ResourceNotFoundException, InvalidIdException {
        vs.deleteThisVideo(videoId);
        return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "video with videoId "+videoId+" deleted success.", request.getRequestURI());
    }
}
