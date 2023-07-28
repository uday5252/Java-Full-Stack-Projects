package com.project.ZeeWebApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ZeeWebApplication.Entity.CommentEntity;
import com.project.ZeeWebApplication.Service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
		name = "Comment API controller",
		description = "This contains all the End points of the comment Application"
		)
@RestController
public class CommentController {

	@Autowired
	CommentService cs;
	
	@PostMapping("/api/comment/user/{userid}/video/{videoid}")
	@ApiResponse
	(
		responseCode = "201"
	)
	@Operation
	(
		summary = "insert the comment data",
		description = "insert the comment data form user side"
	)
	public ResponseEntity<String> CommentTheVideo(@RequestBody CommentEntity centity,@PathVariable("userid") int uid,@PathVariable("videoid") int vid)
	{
		cs.CommentedThevideoBy(centity,uid,vid);
		
		cs.sendDataToCommentTopic(uid,vid);
		
		return new ResponseEntity<>("User commented sucessfully",HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/deletecomment/user/{userid}/video/{videoid}")
	@ApiResponse
	(
		responseCode = "200"
	)
	@Operation
	(
		summary = "delete the comment ",
		description = "delete the comment data form user side"
	)
	public ResponseEntity<String> deleteTheCommentTheVideo(@PathVariable("userid") int uid,@PathVariable("videoid") int vid)
	{
		cs.deleteTheCommentedvideoBy(uid,vid);
		
		return new ResponseEntity<>("User deleted the commented sucessfully",HttpStatus.ACCEPTED);
	}
}
