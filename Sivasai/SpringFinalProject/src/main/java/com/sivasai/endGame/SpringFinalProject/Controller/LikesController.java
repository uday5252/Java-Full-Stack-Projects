package com.sivasai.endGame.SpringFinalProject.Controller;

import com.sivasai.endGame.SpringFinalProject.Entity.Likes;
import com.sivasai.endGame.SpringFinalProject.Entity.User;
import com.sivasai.endGame.SpringFinalProject.Entity.Video;
import com.sivasai.endGame.SpringFinalProject.Producer.KafkaProducer;
import com.sivasai.endGame.SpringFinalProject.ResponseGenerator.ResponseHandler;
import com.sivasai.endGame.SpringFinalProject.Service.LikeService;
import com.sivasai.endGame.SpringFinalProject.Service.UserService;
import com.sivasai.endGame.SpringFinalProject.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikesController {

    @Autowired
    LikeService likeService;

    @Autowired
    UserService userService;

    @Autowired
    VideoService videoService;

    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<?> createLike(@RequestParam("userId")int userId, @RequestParam("videoId")int videoId){
        if(userService.existsById(userId) && videoService.ifExists(videoId)){
            User user = userService.getUserById(userId);
            Video video = videoService.getVideoById(videoId);

            if(likeService.checkIfExistsByUserAndVideo(user, video)){
                return ResponseHandler.generateResponse("User already likes this video", HttpStatus.NOT_IMPLEMENTED, null);
            }

            Likes like = new Likes(user, video);
            Likes likes1 = likeService.saveLike(like);
            kafkaProducer.sendDataToUserLikes("New like created" + likes1.toString());
            return ResponseHandler.generateResponse("Created new like", HttpStatus.CREATED, likes1.toString());
        }
        else {
            return ResponseHandler.generateResponse("Error creating like user or video not found", HttpStatus.NOT_FOUND, false);
        }
    }

    @GetMapping("/user-{userId}")
    public ResponseEntity<?> getAllUserLikes(@PathVariable("userId")int userId){
        if(userService.existsById(userId)){
            User user1 = userService.getUserById(userId);
            if(likeService.checkIfExistsByUser(user1)){
                List<Likes> userLikesList = likeService.getLikesByUser(user1);
                return ResponseHandler.generateResponse("here are all likes by User", HttpStatus.OK, userLikesList.toString());
            }
            else {
                return ResponseHandler.generateResponse("user hasn't liked any video", HttpStatus.NOT_FOUND, null);
            }
        }
        else {
            return ResponseHandler.generateResponse("User not found check user ID given", HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping("/video-{videoId}")
    public ResponseEntity<?> getAllVideoLikes(@PathVariable("videoId")int videoId){
        if(videoService.ifExists(videoId)){
            Video v = videoService.getVideoById(videoId);

            if(likeService.checkIfExistsByVideo(v)){
                List<Likes> videoLikesList = likeService.getLikesByVideo(v);
                return ResponseHandler.generateResponse("All likes related to video", HttpStatus.OK, videoLikesList.toString());
            }
            else {
                return ResponseHandler.generateResponse("No likes for this video yet", HttpStatus.NOT_FOUND, null);
            }
        }
        else {
            return ResponseHandler.generateResponse("Video not found check video Id given", HttpStatus.NOT_FOUND, null);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteLike(@PathVariable("userId")int userId, @RequestParam("videoId")int videoId){
        if(userService.existsById(userId) && videoService.ifExists(videoId)){
            User u = userService.getUserById(userId);
            Video v = videoService.getVideoById(videoId);
            if(likeService.checkIfExistsByUserAndVideo(u, v)){
                likeService.deleteUserLike(u, v);
                return ResponseHandler.generateResponse("Like deleted successfully", HttpStatus.OK, true);
            }
            else {
                return ResponseHandler.generateResponse("user hasn't liked this video yet to delete", HttpStatus.NOT_FOUND, null);
            }
        }
        else {
            return ResponseHandler.generateResponse("Error creating like user or video not found", HttpStatus.NOT_FOUND, false);
        }
    }
}
