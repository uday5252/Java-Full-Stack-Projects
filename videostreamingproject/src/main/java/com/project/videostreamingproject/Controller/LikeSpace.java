package com.project.videostreamingproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.videostreamingproject.repository.GenreRepository;
import com.project.videostreamingproject.repository.UserRepository;
import com.project.videostreamingproject.repository.VideoRepository;
import com.project.videostreamingproject.service.CommentService;
import com.project.videostreamingproject.service.GenreService;
import com.project.videostreamingproject.service.LikeService;
import com.project.videostreamingproject.service.UserService;
import com.project.videostreamingproject.service.VideoService;

@RestController
public class LikeSpace {
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
	
	 @PostMapping("/api/likevideo/{uid}/{vid}")
	 public ResponseEntity<String> likevideo(@PathVariable ("uid") int uid,@PathVariable ("vid") int vid)
	 {    
		 ls.likevideo(uid, vid);
		 return new ResponseEntity<>("video Liked SUCESSFULLY",HttpStatus.OK);
	 }
	 
	 @DeleteMapping("/api/dislike/{uid}/{vid}")
	 public ResponseEntity<String> dislikevideo(@PathVariable ("uid") int uid,@PathVariable ("vid") int vid)
	 {    
		 
		 ls.dislike(uid, vid);
		 return new ResponseEntity<>("video disLiked SUCESSFULLY",HttpStatus.OK);
	 }
}
