package com.abc.demo.VIDEO.STREAMING.PLATFORM.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.VideoEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.likeEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.userEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.REPOSITORY.likeRepository;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE.likeService;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE.userService;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE.videoService;


@Controller
public class likeController {
	
	@Autowired
	likeService ls;
	
	@Autowired
	videoService vs;
	
	@Autowired
	userService us;
	
	@Autowired
	likeRepository lr;
	
	@Autowired    //offset,message
	KafkaTemplate<String,String> kt;
	
	
	@PostMapping("/videos/{videoId}/addlike/{userId}")
	public ResponseEntity<String> videoAddLike(@PathVariable("videoId")int videoId,@PathVariable("userId")int userId) {
		
	    try {
	        VideoEntity video = vs.getVideoById(videoId);
	        userEntity user = us.getUserById(userId);

	        if (video == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid video");
	        }

	        if (user == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user");
	        }

	        boolean hasLiked = ls.hasUserLikedVideo(videoId, userId);
	        
	        if (hasLiked) {
	        	
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User has already liked the video");
	            
	        } else {
	        	
	            likeEntity like = new likeEntity();
	            like.setUserLiked(user);
	            like.setVideolikes(video);
	            ls.addlike(like);

	    		String message=user.getUsername()+" liked "+video.getTitle()+" video";
	    		
	    		
	    		kt.send("user_likes", message);
	            return new ResponseEntity<>("Liked video successfully", HttpStatus.OK);
	            
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
		
	}
	
	

	@DeleteMapping("/videos/{videoId}/unlike/{userId}")
	public ResponseEntity<String> Removelike(@PathVariable("videoId")int videoId,@PathVariable("userId")int userId) {
		

		VideoEntity video = vs.getVideoById(videoId);
        userEntity user = us.getUserById(userId);
        
		boolean hasLiked = ls.hasUserLikedVideo(videoId, userId);
		
		if(hasLiked) {
		
		  ls.unlikeVideo(videoId,userId);
		  
		  String message=user.getUsername()+" unliked "+video.getTitle()+" video";
  		
  		
  		  kt.send("user_likes", message);
		
		  return new ResponseEntity<>("unliked video succesfully",HttpStatus.OK);
		
		}else {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Like not found for the given user and video");
	    }
		
	}

}
