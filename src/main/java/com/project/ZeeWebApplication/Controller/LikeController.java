package com.project.ZeeWebApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ZeeWebApplication.Service.LikeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
		name = "Like API controller",
		description = "This contains all the End points of the like Application"
		)

@RestController
public class LikeController {

	@Autowired
	LikeService ls;
	
	@PostMapping("/api/liked/user/{userid}/video/{videoid}")
	@ApiResponse
	(
		responseCode = "201"
	)
	@Operation
	(
		summary = "insert the like data",
		description = "insert the like data form user side"
	)
	public ResponseEntity<String> LikeTheVideo(@PathVariable("userid") int uid,@PathVariable("videoid") int vid)
	{
		if(ls.HasLiked(uid,vid))
		{
			return new ResponseEntity<>(" user already liked the post ",HttpStatus.CREATED);
		}
		else
		{
		    ls.userLikedTheVideo(uid,vid);
		    
		    ls.sendDataToLikeTopic(uid,vid);
		    
		    return new ResponseEntity<>("user liked post sucessfully",HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/api/unliked/user/{userid}/video/{videoid}")
	@ApiResponse
	(
		responseCode = "200"
	)
	@Operation
	(
		summary = "delete the like ",
		description = "delete the like data form user side"
	)
	public ResponseEntity<String> UnLikeTheVideo(@PathVariable("userid") int uid,@PathVariable("videoid") int vid)
	{
		 ls.userUnLikedTheVideo(uid,vid);
		 return new ResponseEntity<>("user unliked the post sucessfully",HttpStatus.CREATED);

	}
	
	
}
