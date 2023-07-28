package com.project.videostreamingproject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.videostreamingproject.entity.Genre;
import com.project.videostreamingproject.service.CommentService;
import com.project.videostreamingproject.service.GenreService;
import com.project.videostreamingproject.service.LikeService;
import com.project.videostreamingproject.service.UserService;
import com.project.videostreamingproject.service.VideoService;

@RestController
public class GenreSpace {
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
	
	@PostMapping("/api/admin/genre")
	public ResponseEntity<String> createGenre(@RequestBody Genre g)
	{
		Gs.createdgenre(g);
		return new ResponseEntity<>("NEW GENRE ADDED SUCESSFULLY",HttpStatus.CREATED);
	}
	
	@GetMapping("/api/genre")
	public ResponseEntity<List<Genre>> listallgenre()
	{
		List<Genre> g=Gs.findallgenres();
		return new ResponseEntity<>(g,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/api/admin/genres/{genreid}")
	public ResponseEntity<String> updateGenre(@RequestBody Genre g, @PathVariable ("genreid") int id)
	{
		Gs.update(g,id);
		return new ResponseEntity<>("GENRE UPDATED SUCESSFULLY",HttpStatus.OK);
	}
	
	@DeleteMapping("/api/admin/genres/{genreid}")
	public ResponseEntity<String> deletegenre(@PathVariable ("genreid") int id)
	{   
		Gs.delete(id);
		return new ResponseEntity<>("GENRE DELETED SUCESSFULLY",HttpStatus.OK);
	}
}
