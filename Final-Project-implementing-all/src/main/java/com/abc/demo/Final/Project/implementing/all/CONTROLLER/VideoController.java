package com.abc.demo.Final.Project.implementing.all.CONTROLLER;

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

import com.abc.demo.Final.Project.implementing.all.Entity.VideoEntity;
import com.abc.demo.Final.Project.implementing.all.SERVICE.VideoService;

@RestController
public class VideoController {

	
	@Autowired
	VideoService vs;
	
	@PostMapping("/add/video/genre/{gid}/user/{uid}")
	public ResponseEntity<String> addVideo(@RequestBody VideoEntity ve,@PathVariable("gid") int gid,@PathVariable("uid") int uid){
		vs.Videoadd(ve, gid, uid);
		return new ResponseEntity<>("Video Added successfully",HttpStatus.CREATED);
	}
	
	@GetMapping("/get/video/{vid}")
	public ResponseEntity<VideoEntity> getVideo(@PathVariable("vid") int vid){
		VideoEntity ve = vs.Videoget(vid);
		return new ResponseEntity<>(ve,HttpStatus.OK);
	}
	
	@GetMapping("/get/allVideosDetails")
	public ResponseEntity<List<VideoEntity>> getAllVideo(){
		List<VideoEntity> ve = vs.Videos();
		return new ResponseEntity<>(ve,HttpStatus.OK);
	}
	
	@GetMapping("/get/allVideoNames")
	public ResponseEntity<List<String>> nameVideos(){
		List<String> s = vs.videoNames();
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	@GetMapping("/get/sameGenre/{gid}/videos")
	public ResponseEntity<List<VideoEntity>> SameGenreVideo(@PathVariable("gid") int gid){
		List<VideoEntity> s = vs.videoByGenre(gid);
	    return new ResponseEntity<>(s,HttpStatus.OK);		
	}
	
	@DeleteMapping("/delete/video/{vid}")
	public ResponseEntity<String> deleteVideo(@PathVariable("vid") int vid){
		vs.Videodelete(vid);
		return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
	}
}
