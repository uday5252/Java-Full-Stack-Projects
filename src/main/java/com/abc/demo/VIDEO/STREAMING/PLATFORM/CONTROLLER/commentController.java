package com.abc.demo.VIDEO.STREAMING.PLATFORM.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.VideoEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.commentEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.userEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.REPOSITORY.commentRepository;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE.commentService;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE.userService;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE.videoService;

@Controller
public class commentController {
	
	@Autowired
	commentService  cs;

	@Autowired
	videoService vs;
	
	@Autowired
	userService us;
	
	@Autowired
	commentRepository  cr;
	
	
	@Autowired    //offset,message
	KafkaTemplate<String,String> kt;
	
	@PostMapping("/videos/{videoId}/addcomment/{userId}")
	public ResponseEntity<String> addComment(@PathVariable("videoId")int videoId,@PathVariable("userId")int userId,@RequestBody commentEntity ce){
		
		VideoEntity video = vs.getVideoById(videoId);
        userEntity user = us.getUserById(userId);

        if (video == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid video");
        }

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user");
        }
        
        commentEntity comment=new commentEntity();
        
        comment.setUsercommented(user);
        
        comment.setVideocomments(video);
        
        comment.setComment(ce.getComment());
        
        cs.addCommentToVideo(comment);
        

		String message=user.getUsername()+" has commented on  "+video.getTitle()+" video and the comment is "+comment.getComment();
		
		
		kt.send("user_comments", message);
        
        return new ResponseEntity<>("comment added successfully", HttpStatus.OK);
        
	}

	@DeleteMapping("/videos/{videoId}/deletecomment/{userId}")
	public ResponseEntity<String> deleteComment(@PathVariable("videoId")int videoId,@PathVariable("userId")int userId){
		
		userEntity user = us.getUserById(userId);
		
		VideoEntity video = vs.getVideoById(videoId);
		
		boolean hascommented=cs.hasUserCommentedVideo(videoId, userId);
		
		if(hascommented) {
			cs.deleteCommentToVideo(videoId, userId);
			
			String message=user.getUsername()+" has deleted his comment on "+video.getTitle()+" video";
			
			kt.send("user_comments", message);
			
			return new ResponseEntity<>("comment deleted successfully", HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>("comment not found", HttpStatus.OK);
		}
		

		

        
		
	}
	
	
	
	@PutMapping("/videos/updateComment")
	public ResponseEntity<String> updateComment(@RequestBody commentEntity ce) {
		
		cs.editComment(ce,ce.getCommentId());
		
	return new ResponseEntity<>("comment updated sucessfully", HttpStatus.OK);
	
	}
	
	

}
