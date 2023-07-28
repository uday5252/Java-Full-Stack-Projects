package com.abc.project1.controller;

import com.abc.project1.ApiResponse.ResponseHandler;
import com.abc.project1.ExceptionHandling.CustomException.InvalidIdException;
import com.abc.project1.ExceptionHandling.CustomException.ResourceNotFoundException;
import com.abc.project1.entity.Comment;
import com.abc.project1.entity.LikeEntity;
import com.abc.project1.entity.Video;
import com.abc.project1.service.CommentService;
import com.abc.project1.service.LikeService;
import com.abc.project1.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/videos")
public class VideoMetadataController {
    @Autowired
    VideoService vs;
    @Autowired
    LikeService ls;
    @Autowired
    CommentService cs;

    @Autowired
    KafkaTemplate<String, Object> kt;

    @GetMapping("/{videoId}")
    public ResponseEntity<Map<String, Object>> getVideoDetails(@PathVariable("videoId") int videoId, HttpServletRequest request) throws ResourceNotFoundException, InvalidIdException {
        Video video = vs.getThisVideo(videoId);
        return ResponseHandler.generateResponse(video, HttpStatus.OK, "video details fetched success.", request.getRequestURI());
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllVideos(HttpServletRequest request){
        List<Video> videos = vs.getAllVideos();
        return ResponseHandler.generateResponse(videos, HttpStatus.OK, "all video fetched success.", request.getRequestURI());
    }

    @PostMapping("/{videoId}/like")
    public ResponseEntity<Map<String, Object>> likeVideo(@PathVariable("videoId") int videoId, @RequestBody LikeEntity like, HttpServletRequest request) throws InvalidIdException {
        LikeEntity savedLike = ls.likeThisVideo(videoId, like);
        kt.send("user_likes", "like notification", savedLike + " like added successfully.");
        return ResponseHandler.generateResponse(savedLike, HttpStatus.CREATED, savedLike.getUser().getUsername() + " liked the video with videoId "+like.getVideoId()+".", request.getRequestURI());
    }

    @PostMapping("/{videoId}/comment")
    public ResponseEntity<Map<String, Object>> commentOnVideo(@PathVariable("videoId") int videoId, @RequestBody Comment comment, HttpServletRequest request) throws InvalidIdException {
        Comment savedComment = cs.commentOnThisVideo(videoId, comment);
        kt.send("user_comments", "comment notification", savedComment + " comment added successfully.");
        return ResponseHandler.generateResponse(savedComment, HttpStatus.CREATED, savedComment.getUser().getUsername() + " commented on video with videoId "+savedComment.getVideoId()+".", request.getRequestURI());
    }

    @GetMapping("/{videoId}/likes")
    public ResponseEntity<Map<String, Object>> getALlLikes(@PathVariable("videoId") int videoId, HttpServletRequest request) throws InvalidIdException {
        List<LikeEntity> likes = ls.getAllLikesOnVideo(videoId);
        return ResponseHandler.generateResponse(likes, HttpStatus.OK, "all likes on video with videoId "+videoId+" fetched success.", request.getRequestURI());
    }

    @GetMapping("/{videoId}/comments")
    public ResponseEntity<Map<String, Object>> getAllComments(@PathVariable("videoId") int videoId, HttpServletRequest request) throws InvalidIdException {
        List<Comment> comments = cs.getAllCommentsOnVideo(videoId);
        return ResponseHandler.generateResponse(comments, HttpStatus.OK, "all comments on video with videoId "+ videoId + " fetched success.", request.getRequestURI());
    }

    @GetMapping(value = "/readVideo/{videoId}", produces = "video/mp4")
    public Mono<Resource> getThisVideo(@PathVariable("videoId") int videoId) throws ResourceNotFoundException, InvalidIdException {
        return vs.readVideo(videoId);
    }

    @GetMapping(value = "/{videoId}/play", produces = "video/mp4")
    public ModelAndView playThisVideo(@PathVariable("videoId") int videoId){
        ModelAndView mav = new ModelAndView("playVideoView");
        mav.addObject("videoId", videoId);
        return mav;
    }
}
