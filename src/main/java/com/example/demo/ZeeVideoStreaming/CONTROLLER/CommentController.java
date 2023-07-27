package com.example.demo.ZeeVideoStreaming.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ZeeVideoStreaming.ENTITY.CommentEntity;
import com.example.demo.ZeeVideoStreaming.SERVICE.CommentService;

@RestController
public class CommentController {

	@Autowired
	CommentService cs;

	@PostMapping("/api/videos/{videoId}/comment")
	public ResponseEntity<Object> addComment(@PathVariable("videoId") long vid, @RequestBody CommentEntity c) {
		cs.createCommentInDB(vid, c);
		return ResponseHandler.generateResponse("comment added successfully", HttpStatus.CREATED, c, true);
	}

	@PutMapping("/api/comments/{commentId}/")
	public ResponseEntity<Object> updateComment(@PathVariable("commentId") long id, @RequestBody CommentEntity c) {
		CommentEntity updatedComment = cs.updateCommentInDB(id, c);
		return ResponseHandler.generateResponse("comment updated successfully", HttpStatus.OK, updatedComment, true);
	}
	
	@DeleteMapping("/api/comment/{commentId}/")
	public ResponseEntity<Object> deleteComment(@PathVariable("commentId") long id) {
		cs.deleteCommentInDB(id);
		return ResponseHandler.generateResponse("comment deleted successfully", HttpStatus.OK, id, true);
	}
}
