package com.project.videostreamingproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.videostreamingproject.entity.User;
import com.project.videostreamingproject.entity.VideoTabel;
import com.project.videostreamingproject.service.CommentService;
import com.project.videostreamingproject.service.GenreService;
import com.project.videostreamingproject.service.LikeService;
import com.project.videostreamingproject.service.UserService;
import com.project.videostreamingproject.service.VideoService;

@RestController
public class CommentSpace {
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
	
	
	@PostMapping("/api/commentvideo/{uid}/{vid}/{comment}")
	 public ResponseEntity<String> commentvideoalongwithuserid(@PathVariable ("uid") int uid,@PathVariable ("vid") int vid,@PathVariable ("comment") String comment)
	 {    
		
		 User u=Us.find(uid);
		 VideoTabel v=Vs.find(vid);
		 Cs.setcomment(u, v, comment);
		 return new ResponseEntity<>("COMMENTED  SUCESSFULLY",HttpStatus.OK);
	 }
	 

	 @DeleteMapping("/api/commentvideo/{uid}/{vid}")
	 public ResponseEntity<String> uncomment(@PathVariable ("uid") int uid,@PathVariable ("vid") int vid)
	 {    
		
		 User u=Us.find(uid);
		 VideoTabel v=Vs.find(vid);
		 Cs.deletecomment(u, v);
		 return new ResponseEntity<>("UNCOMMENTED  SUCESSFULLY",HttpStatus.OK);
	 }
}
