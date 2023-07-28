package com.example.endtoendproject.CONTROLLER;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.endtoendproject.ENTITY.Video;
import com.example.endtoendproject.PRODUCER.KafkaProducer;
import com.example.endtoendproject.SERVICE.VideoService;

//Task 3: Video Metadata Management
//Endpoints
//1. `POST /api/admin/videos`: Upload a new video and associate it with a genre (Admin only).
//2. `GET /api/videos/{videoId}`: Get details of a specific video.
//3. `GET /api/videos`: Get a list of all videos.
//4. `GET /api/genres/{genreId}/videos`: Get a list of videos within a specific genre. 5. `PUT /api/admin/videos/{videoId}`: Update video details (Admin only).
//6. `DELETE /api/admin/videos/{videoId}`: Delete a video (Admin only).
@RestController
public class VideoController {
	@Autowired
	VideoService vs;
	@Autowired
	KafkaProducer kp;
	@PostMapping("/api/admin/videos/{genreId}")
	public ResponseEntity<String> insertVideo(@RequestBody Video video, @PathVariable("genreId") int gid){
		vs.uploadVideo(video, gid);
		kp.sendData3("Video has been uploaded");
		System.out.println(video.getTitle());
		return new ResponseEntity<>("Video successfully uploaded" ,HttpStatus.CREATED);
	}
	
	@GetMapping("/api/videos/{videoId}")
	public ResponseEntity<Video> getVideoById(@PathVariable("videoId") int vid) {
		Video video1 =vs.getVideoById(vid); 
		return new ResponseEntity<>(video1 ,HttpStatus.CREATED);
	}
	
	@GetMapping("/api/videos")
	public ResponseEntity<List<Video>> getAllVideos(){ 
		List<Video> videos = vs.getAllVideos();
		return new ResponseEntity<>(videos ,HttpStatus.CREATED);
	}
	
	@GetMapping("/api/genres/{genreId}/videos")
	public List<Video> getVideoByGenre(@PathVariable("genreId") int gid ){
		return vs.getVideoByGenre(gid);
	}
	
	@DeleteMapping("/api/admin/videos/{videoId}")
	public ResponseEntity<String>  deleteVideo(@PathVariable("videoId") int vid){
		vs.deleteVideo(vid);
		return new ResponseEntity<>("Video successfully deleted" ,HttpStatus.CREATED);
	}
	
}
