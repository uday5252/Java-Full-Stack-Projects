package com.sivasai.endGame.SpringFinalProject.Controller;

import com.sivasai.endGame.SpringFinalProject.Entity.Comments;
import com.sivasai.endGame.SpringFinalProject.Entity.User;
import com.sivasai.endGame.SpringFinalProject.Entity.Video;
import com.sivasai.endGame.SpringFinalProject.Producer.KafkaProducer;
import com.sivasai.endGame.SpringFinalProject.ResponseGenerator.ResponseHandler;
import com.sivasai.endGame.SpringFinalProject.Service.CommentService;
import com.sivasai.endGame.SpringFinalProject.Service.UserService;
import com.sivasai.endGame.SpringFinalProject.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    @Autowired
    CommentService commentService;

    @Autowired
    VideoService videoService;

    @Autowired
    UserService userService;

    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody String content ,@RequestParam("userId")int userId, @RequestParam("videoId")int videoId){
        if(userService.existsById(userId) && videoService.ifExists(videoId)) {
            User user = userService.getUserById(userId);
            Video video = videoService.getVideoById(videoId);

            Comments newComment = new Comments(video, user, content);
            Comments savedComment = commentService.createComment(newComment);
            kafkaProducer.sendDataToUserComments("New comment created"+savedComment.toString());
            return ResponseHandler.generateResponse("New comment created", HttpStatus.CREATED, savedComment.toString());
        }
        else {
            return ResponseHandler.generateResponse("User or video does not exist", HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping("/video-{videoId}")
    public ResponseEntity<?> getAllCommentOfVideo(@PathVariable("videoId")int videoId){
        if(videoService.ifExists(videoId)) {
            Video v = videoService.getVideoById(videoId);
            if (commentService.checkIfExists(v)) {
                List<Comments> commentsList = commentService.getAllVideoComments(v);
                return ResponseHandler.generateResponse("Here are all comments of the video", HttpStatus.OK, commentsList.toString());
            } else {
                return ResponseHandler.generateResponse("No comments to this video yet", HttpStatus.NOT_FOUND, null);
            }
        }
        else {
            return ResponseHandler.generateResponse("Video id error", HttpStatus.NOT_FOUND, null);
        }
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId")int commentId, @RequestBody String content)
    {
        if(commentService.checkIfExistsById(commentId)){
            Comments updatedComment = commentService.updateCommentById(commentId, content);
            kafkaProducer.sendDataToUserComments("Comment updated successfully: "+updatedComment.toString());
            return ResponseHandler.generateResponse("updated comment successfully", HttpStatus.OK, updatedComment.toString());
        }
        else {
            return ResponseHandler.generateResponse("Comment Id error", HttpStatus.NOT_FOUND, false);
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId")int commentId){

        if(commentService.checkIfExistsById(commentId)){
            commentService.deleteById(commentId);
            return ResponseHandler.generateResponse("Comment deleted successfully", HttpStatus.OK, true);
        }
        else {
            return ResponseHandler.generateResponse("Comment Id error", HttpStatus.NOT_FOUND, false);
        }
    }
}
