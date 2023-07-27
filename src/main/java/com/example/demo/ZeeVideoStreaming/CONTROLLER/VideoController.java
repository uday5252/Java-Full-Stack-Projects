package com.example.demo.ZeeVideoStreaming.CONTROLLER;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.example.demo.ZeeVideoStreaming.ENTITY.CommentEntity;
import com.example.demo.ZeeVideoStreaming.ENTITY.LikeEntity;
import com.example.demo.ZeeVideoStreaming.ENTITY.VideoEntity;
import com.example.demo.ZeeVideoStreaming.SERVICE.CommentService;
import com.example.demo.ZeeVideoStreaming.SERVICE.LikeService;
import com.example.demo.ZeeVideoStreaming.SERVICE.VideoService;

@Controller
public class VideoController {

	@Autowired
	VideoService vs;
	
	@Autowired
	LikeService ls;
	
	@Autowired
	CommentService cs;
	
	@PostMapping("/api/admin/videos")
	public ResponseEntity<Object> addVideo(@RequestBody VideoEntity v) {
//		System.out.println(v);
		vs.addVideoIntoDB(v);
		return ResponseHandler.generateResponse("video added successfully", HttpStatus.CREATED, v, true);
	}

	@GetMapping("/api/videos/{videoId}")
	public String getVideo(@PathVariable("videoId") long id,Model m) {
		
//		return ResponseHandler.generateResponse("video fetched successfully", HttpStatus.OK, vs.getVideoFromDB(id),
//				true);
		
		Map<String,Object> mapData= new HashMap();
		VideoEntity v = vs.getVideoFromDB(id);
		
		mapData.put("v",v);
		
		List<LikeEntity> likes = ls.getAllLikesByVideoIDFromDB(id);
		mapData.put("likes", likes);
		
		List<CommentEntity> comments = cs.getCommentsByVideoID(id);
		
		mapData.put("comments",comments);
//		m.addAttribute("v",v);
		m.addAllAttributes(mapData);
		return "playvideo";
	}

	/*
	@GetMapping("/api/videos")
	public ResponseEntity<Object> getAllVideos() {
		return ResponseHandler.generateResponse("All videos are fetched successfully", HttpStatus.OK,
				vs.getAllVideosFromDB(), true);
	}
	
	*/
	
	@GetMapping("/api/videos")
	public String getAllVideos(Model m) {
//		return ResponseHandler.generateResponse("All videos are fetched successfully", HttpStatus.OK,
//				vs.getAllVideosFromDB(), true);
		m.addAttribute("videos",vs.getAllVideosFromDB());
		return "showvideos";
	}
	@PutMapping("/api/admin/videos/{videoId}")
	public ResponseEntity<Object> updateVideo(@PathVariable("videoId") long id, @RequestBody VideoEntity v) {
		VideoEntity v1 = vs.updateVideoInDB(id, v);
		return ResponseHandler.generateResponse("video updated successfully", HttpStatus.OK, v1, true);
	}

	@DeleteMapping("/api/admin/videos/{videoId}")
	public ResponseEntity<Object> deleteVideo(@PathVariable("videoId") long id) {
		boolean status = vs.deleteVideoInDB(id);
		if (status)
			return ResponseHandler.generateResponse("video deleted successfully", HttpStatus.OK, id, status);

		return ResponseHandler.generateResponse("video DNE :(", HttpStatus.BAD_REQUEST, id, status);

	}
	
	@GetMapping("/api/genres/{genreId}/videos")
	public ResponseEntity<Object> getVideosByGenreId(@PathVariable("genreId") long gid) {
		List<VideoEntity> videos = vs.getVideosByGenreFromDB(gid);

		return ResponseHandler.generateResponse("videos retrieved successfully", HttpStatus.OK, videos, true);

	}
}
