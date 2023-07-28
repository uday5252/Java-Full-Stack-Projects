package com.project.zee.Video.Streaming.Platform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.zee.Video.Streaming.Platform.Service.LikeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class LikesController {
	
	@Autowired
	LikeService ls;
	
	@ApiResponse
    (
        responseCode = "201"
    )
    @Operation
    (
        summary = "Like a Video",

        description = "Like the video by entering the user id and videoid in the path "
    )
	
	@PostMapping("/api/user/{userid}/like/video/{videoid}")
	public ResponseEntity<String> LikeAVideo(@PathVariable int userid,@PathVariable int videoid)
	{
		ls.LikeAVideo(videoid, userid);
		ls.sendLikesUpdateToLikesTopic(userid,videoid);
		 return new ResponseEntity<>("You liked this video", HttpStatus.CREATED);
	}
	
	 @Operation
	    (
	        summary = "UnLike a Video",

	        description = "UnLike the video by entering the user id and videoid in the path "
	    )
	
	@DeleteMapping("/api/user/{userid}/unlike/video/{videoid}")
	public ResponseEntity<String> UnLikeAVideo(@PathVariable int userid,@PathVariable int videoid)
	{
		
		ls.deleteLike(videoid, userid);
		ls.sendUnlikesUpdateToLikesTopic(userid,videoid);
		 return new ResponseEntity<>("You unliked this video", HttpStatus.CREATED);
	}
	
	

}
