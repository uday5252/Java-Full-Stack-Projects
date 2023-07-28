package com.project.videostreamingproject.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.videostreamingproject.entity.VideoTabel;
import com.project.videostreamingproject.helpfulclasses.VideoTabelDuplicate;
import com.project.videostreamingproject.service.CommentService;
import com.project.videostreamingproject.service.GenreService;
import com.project.videostreamingproject.service.LikeService;
import com.project.videostreamingproject.service.UserService;
import com.project.videostreamingproject.service.VideoService;

@RestController
public class VideoSpace {
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
	
	
	@PostMapping("/api/admin/videos")
	public ResponseEntity<String> uploadvideo(@RequestBody VideoTabelDuplicate vt) throws IOException
	{  
		
		Vs.uploadvideos(vt);
		
		return new ResponseEntity<>("Video Addded  SUCESSFULLY",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/videos")
	public ResponseEntity<List<VideoTabel>> listallvideo()
	{
		List<VideoTabel> g=Vs.findall();
		return new ResponseEntity<>(g,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/videos/{vid}")
	public ResponseEntity<VideoTabel> videooftype(@PathVariable ("vid") int id)
	{
		VideoTabel g=Vs.find(id);
		return new ResponseEntity<>(g,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/genre/{genreid}/videos")
	public ResponseEntity<List<VideoTabel>> listallvideoofspecificgenre(@PathVariable ("genreid") int id)
	{
		List<VideoTabel> g=Vs.findallofgenre(id);
		return new ResponseEntity<>(g,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/admin/videos/{vid}")
	public ResponseEntity<String> deletevideo(@PathVariable ("vid") int id)
	{   
		Vs.delete(id);
		return new ResponseEntity<>("video DELETED SUCESSFULLY",HttpStatus.OK);
	}
}
