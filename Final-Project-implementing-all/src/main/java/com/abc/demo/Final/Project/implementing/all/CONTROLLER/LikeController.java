package com.abc.demo.Final.Project.implementing.all.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.Final.Project.implementing.all.Entity.LikeEntity;
import com.abc.demo.Final.Project.implementing.all.SERVICE.LikeService;

@RestController
public class LikeController {

	@Autowired
	LikeService ls;
	
	@PostMapping("/like/user/{uid}/video/{vid}")
	public ResponseEntity<String> Like(@RequestBody LikeEntity like,@PathVariable("uid") int uid,@PathVariable("vid") int vid) {
		ls.addLike(like, uid, vid);
		return new ResponseEntity<>("Liked the video",HttpStatus.CREATED);
	}
}
