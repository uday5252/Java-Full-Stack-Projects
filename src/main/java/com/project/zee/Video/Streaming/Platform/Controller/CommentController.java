package com.project.zee.Video.Streaming.Platform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.zee.Video.Streaming.Platform.Entity.CommentEntity;
import com.project.zee.Video.Streaming.Platform.Service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class CommentController {
	
	@Autowired
	CommentService cs;
	
	@ApiResponse
    (
        responseCode = "201"
    )
    @Operation
    (
        summary = "Comment a Video",

        description = "Comment the video by entering the user id and videoid in the path "
    )
	
	@PostMapping("/api/user/{userid}/comment/video/{videoid}")
	public ResponseEntity<String> AddComment(@RequestBody CommentEntity comment,@PathVariable int userid,@PathVariable int videoid)
	{
		cs.CommentAVideo(comment, videoid, userid);
		cs.sendCommentsUpdatetoCommentsTopic(userid, videoid);
		return new ResponseEntity<>("Comment added succesfully", HttpStatus.CREATED);
	}
	
    @Operation
    (
        summary = "Update a Comment",

        description = "Update the comment by entering the updated description of the comment "
    )
	
	@PutMapping("/api/user/{userid}/update/comment/video/{videoid}")
	public ResponseEntity<String> UpdateComment(@RequestBody CommentEntity comment,@PathVariable int userid,@PathVariable int videoid)
	{
		cs.UpdateComment(comment, videoid,userid);
		cs.sendEditedCommentsUpdateToCommentsTopic(userid, videoid);
		return new ResponseEntity<>("Comment updated succesfully", HttpStatus.CREATED);
		
	}
    
    @Operation
    (
        summary = "Delete a Comment",

        description = "Delete the comment to the video by the user  "
    )
	
	@DeleteMapping("/api/user/{userid}/uncomment/video/{videoid}")
	public ResponseEntity<String> DeleteComment(@PathVariable int userid,@PathVariable int videoid)
	{
		cs.deleteComment(userid,videoid);
		cs.sendDeletedCommentsUpdateToCommentsTopic(userid, videoid);
		
		return new ResponseEntity<>("Comment deleted succesfully", HttpStatus.OK);
		
	}
	
	

}
