package com.abc.demo.Video.Management.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.Video.Management.Project.service.commentService;

@RestController
public class commentController {
	@Autowired
	commentService cs;
	@PostMapping("/api/video/comment/{userId}/{videoId}")
	public ResponseEntity<String> addComment(@PathVariable("userId") int userId,@PathVariable("videoId") int videoId)
	{
		String s=cs.addComment(userId, videoId);
		return new ResponseEntity<String>(s,HttpStatus.CREATED);
	}
	@DeleteMapping("/api/video/comment/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentId") int commentId)
	{
		String s=cs.deleteComment(commentId);
		return new ResponseEntity<String>(s,HttpStatus.CREATED);
	}
}
