package com.example.demo.ZeeVideoStreaming.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ZeeVideoStreaming.ENTITY.LikeEntity;
import com.example.demo.ZeeVideoStreaming.ENTITY.UserEntity;
import com.example.demo.ZeeVideoStreaming.ENTITY.VideoEntity;
import com.example.demo.ZeeVideoStreaming.SERVICE.LikeService;
import com.example.demo.ZeeVideoStreaming.SERVICE.UserService;
import com.example.demo.ZeeVideoStreaming.SERVICE.VideoService;

@RestController
public class LikeController {

	@Autowired
	LikeService ls;

	@Autowired
	VideoService vs;

	@Autowired
	UserService us;

	@PostMapping("/api/videos/{videoId}/like")
	public ResponseEntity<Object> createLike(@PathVariable("videoId") long vid, @RequestBody LikeEntity like) {
		VideoEntity video = vs.getVideoFromDB(vid);
		if (video == null) {
			return ResponseHandler.generateResponse("your video DNE :(", HttpStatus.BAD_REQUEST, like, false);
		}
		LikeEntity likeFromDB = ls.getLikeByUserAndVideoFromDB(like.getUser().getId(), vid);
		if (likeFromDB != null) {
			return ResponseHandler.generateResponse("you have already liked the video", HttpStatus.BAD_REQUEST, like,
					false);
		}
		like.setVideo(video);

		UserEntity user = us.getUserFromDatabase(like.getUser().getId());

		like.setUser(user);
		
		ls.createLikeInDB(like);
		return ResponseHandler.generateResponse("you liked the video", HttpStatus.CREATED, like, true);
	}

	@DeleteMapping("/api/videos/{videoId}/unlike")
	public ResponseEntity<Object> deleteLike(@PathVariable("videoId") long vid, @RequestBody LikeEntity like) {
		long uid = like.getUser().getId();
		LikeEntity likeFromDB = ls.getLikeByUserAndVideoFromDB(uid, vid);
		if (likeFromDB == null) {
			return ResponseHandler.generateResponse("you didn't like the video previously", HttpStatus.BAD_REQUEST,
					like, false);
		}
		ls.deleteLikeByUserAndVideoFromDB(uid, vid);
		return ResponseHandler.generateResponse("you unliked the video", HttpStatus.OK, like, true);
		
	}

	@GetMapping("/api/likes")
	public ResponseEntity<Object> getAllLikes() {
		return ResponseHandler.generateResponse("likes so far", HttpStatus.OK, ls.getAllLikesFromDB(), true);
	}
}
