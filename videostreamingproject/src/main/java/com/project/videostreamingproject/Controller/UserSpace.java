package com.project.videostreamingproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.videostreamingproject.entity.User;
import com.project.videostreamingproject.service.CommentService;
import com.project.videostreamingproject.service.GenreService;
import com.project.videostreamingproject.service.LikeService;
import com.project.videostreamingproject.service.UserService;
import com.project.videostreamingproject.service.VideoService;


@RestController
public class UserSpace {
	@Autowired
	GenreService Gs;
	
	@Autowired
	VideoService Vs;
	
	@Autowired 
	UserService Us;
	
	@Autowired
	LikeService ls;
	
	@Autowired
	CommentService Cs;
	
	@PostMapping("/newuser")
	public ResponseEntity<String> createuser(@RequestBody User g)
	{
		Us.newuser(g);
		return new ResponseEntity<>("NEW user ADDED SUCESSFULLY",HttpStatus.CREATED);
	}
	
	@DeleteMapping("/userdelete/{uid}")
	public ResponseEntity<String> deleteuser(@PathVariable ("uid") int id)
	{
		Us.deleteuser(id);
		return new ResponseEntity<>("user deleted sucessfully",HttpStatus.CREATED);
	}
	
}
