package com.abc.demo.END_PROJECT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.END_PROJECT.Apache_Kafka.KafkaService;
import com.abc.demo.END_PROJECT.entity.CommentsEntity;
import com.abc.demo.END_PROJECT.entity.LikeEntity;
import com.abc.demo.END_PROJECT.entity.UserEntity;
import com.abc.demo.END_PROJECT.service.UserService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class UserController {
	
	@Autowired
	UserService us;
	
	@Autowired
	KafkaService ks;
	
	@Operation
	(
		summary="Add User",
		description="This API Endpoint is used for creating user details"
	)
	@PostMapping("/api/admin/user/add")
	
	public ResponseEntity<String> addUser(@RequestBody UserEntity user){
		us.addUser(user);
		return new ResponseEntity<>("User has been added successfully",HttpStatus.CREATED);
	}
	
	@Operation
	(
		summary="Add like",
		description="This API Endpoint is used for creating like "
	)
	@PostMapping("/api/admin/like")
	public ResponseEntity<String> addLike(@RequestBody LikeEntity le){
		
		if(us.liked(le)) {
		
		us.addLike(le);
		String message = "user with user ID "+le.getUser().getId()+" liked the video with Video ID "+le.getVideo().getId();
		ks.sendDataToUser_LikesTopic(message);
		return new ResponseEntity<>("Like has been added successfully",HttpStatus.CREATED);
		} 
		else {
			return new ResponseEntity<>("Video has been already liked by the user",HttpStatus.CREATED);
		}
	}
	@Operation
	(
		summary="Unlike video",
		description="This API Endpoint is used to unlike video"
	)
	
	@DeleteMapping("/api/admin/unlike")
	public ResponseEntity<String> Unlike(@RequestBody LikeEntity le){
		
		if(us.liked(le)) {
			return new ResponseEntity<>("Video has not been liked",HttpStatus.CREATED);
			}
			else {
				us.unlike(le);
				String message = "user with user ID "+le.getUser().getId()+" has unliked the video with Video ID "+le.getVideo().getId();
				ks.sendDataToUser_LikesTopic(message);
				return new ResponseEntity<>("Video has been unliked by the user",HttpStatus.CREATED);
			}
	}
	
	@Operation
	(
		summary="Add comment",
		description="This API Endpoint is used for creating comment details"
	)
	
	@PostMapping("/api/admin/comment")
	public ResponseEntity<String> addUser(@RequestBody CommentsEntity ce){
		us.addComment(ce);
		String message = "User with user ID  " +ce.getUser().getId()+" commented on the video with ID "+ ce.getVideo().getId()+" saying " +ce.getDescription();
		ks.sendDataToUser_CommentsTopic(message);
		return new ResponseEntity<>("Comment has been added successfully",HttpStatus.CREATED);
	}
	
	
	@Operation
	(
		summary="delete comment",
		description="This API Endpoint is used for deleting comment details"
	)
	@DeleteMapping("/api/admin/delete/comment")
	public ResponseEntity<String> deleteGenre(@RequestBody CommentsEntity ce){
		us.deleteComment(ce);
		String message = "User with user ID  " +ce.getUser().getId()+" deleted his comment on the video with ID "+ ce.getVideo().getId();
		ks.sendDataToUser_CommentsTopic(message);
		return new ResponseEntity<>("Comment has been deleted by user ",HttpStatus.OK);
	}

}
