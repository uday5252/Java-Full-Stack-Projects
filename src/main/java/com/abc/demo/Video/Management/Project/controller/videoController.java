package com.abc.demo.Video.Management.Project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.abc.demo.Video.Management.Project.entity.videoEntity;
import com.abc.demo.Video.Management.Project.service.videoService;

@Controller
public class videoController {
	@Autowired
	videoService vs;
	
	@PostMapping("/api/user/videos/{genreId}/{userId}")
	public ResponseEntity<String> video(@PathVariable("genreId") int genreId, @PathVariable("userId") int userId,
			@RequestBody videoEntity ve) {
		vs.addVideo(ve, genreId, userId);
		return new ResponseEntity<>("Created", HttpStatus.CREATED);
	}
	@DeleteMapping("/api/user/videos/delete/{id}")
	public ResponseEntity<String> deleteVideo(@PathVariable("id") int id)
	{
		vs.deleteVideo(id);
		return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
	}
	@GetMapping("/api/videos/{genreId}/{userId}")
	public ResponseEntity<ArrayList<videoEntity>> video(@PathVariable("genreId") int genreId, @PathVariable("userId") int userId){
		ArrayList<videoEntity> list=vs.allVideo(genreId, userId);
		return new ResponseEntity<ArrayList<videoEntity>>(list, HttpStatus.CREATED);
	}
	@GetMapping("/api/videos/user/{userId}")
	public ResponseEntity<ArrayList<videoEntity>> video(@PathVariable("userId") int userId){
		ArrayList<videoEntity> list=vs.allVideoByUser(userId);
		return new ResponseEntity<ArrayList<videoEntity>>(list, HttpStatus.CREATED);
	}
	@GetMapping("/api/videos/genre/{genreId}")
	public ResponseEntity<ArrayList<videoEntity>> videosByGenre(@PathVariable("genreId") int genreId){
		ArrayList<videoEntity> list=vs.allVideoByGenre(genreId);
		return new ResponseEntity<ArrayList<videoEntity>>(list, HttpStatus.CREATED);
	}
	@GetMapping("/videos")
	public String videoStream(Model m)
	{
		List<videoEntity> list=vs.allVideos();
		m.addAttribute("video",list);
		return "playVideo";
	}
	@GetMapping("/video/user/{userId}")
	public String videoStreamByVideoid(@PathVariable("userId") int userId,Model m)
	{
		ArrayList<videoEntity> list=vs.allVideoByUser(userId);
		m.addAttribute("video",list);
		return "playVideo";
	}
	@GetMapping("/video/genre/{genreId}")
	public String videoStreamByGenreid(@PathVariable("genreId") int genreId,Model m)
	{
		ArrayList<videoEntity> list=vs.allVideoByGenre(genreId);
		m.addAttribute("video",list);
		return "playVideo";
	}
	
}
