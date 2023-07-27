package com.abc.demo.Video.Management.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.Video.Management.Project.service.likeService;

@RestController
public class likeController {
	@Autowired
	likeService ls;
	@PostMapping("/api/user/like/{videoId}/{userId}")
	public ResponseEntity<String> like(@PathVariable ("videoId") int videoId,@PathVariable ("userId") int userId)
	{
		String s=ls.addLike(videoId, userId);
		return new ResponseEntity<String>(s,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/api/user/like/delete/{videoId}/{userId}")
	public ResponseEntity<String> deletelike(@PathVariable ("videoId") int videoId,@PathVariable ("userId") int userId)
	{
		String s=ls.deleteLike(videoId, userId);
		return new ResponseEntity<String>(s,HttpStatus.ACCEPTED);
	}
}
