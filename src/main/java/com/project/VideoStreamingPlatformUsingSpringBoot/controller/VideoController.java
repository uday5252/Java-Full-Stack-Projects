package com.project.VideoStreamingPlatformUsingSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.VideoStreamingPlatformUsingSpringBoot.entity.videosEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.kafka.KafkaService;
import com.project.VideoStreamingPlatformUsingSpringBoot.service.VideoService;

@RestController
public class VideoController {
	@Autowired
	VideoService vs;
	@Autowired
	KafkaService ks;
	
	@PostMapping("/api/admin/videos/add")
	public ResponseEntity<String> addVideo(@RequestBody videosEntity ve){
		vs.addvideo(ve);
		ks.sendDataToVideo_UploadsTopic(ve.getUser().getUserId()+"uploaded the video");
		ks.sendDataToVideo_UploadsTopic(ve.toString());
		return new ResponseEntity<String>("Video added successfully",HttpStatus.CREATED);
	}
	
	@PutMapping("/api/admin/videos/{videoid}/update")
	public ResponseEntity<String> updateVideoDetails(@PathVariable("videoid") int vid, @RequestBody videosEntity ve){
		vs.updatevideoDetails(vid,ve);
		ks.sendDataToVideo_UploadsTopic(ve.getUser().getUserId()+"updated the video");
		ks.sendDataToVideo_UploadsTopic(ve.toString());
		return new ResponseEntity<String>("Video updated successfully",HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/admin/videos/{videoid}/delete")
	public ResponseEntity<String> deleteVideo(@PathVariable("videoid") int vid){
		vs.deletevideo(vid);
		ks.sendDataToVideo_UploadsTopic("video with id "+vid+" is deleted.");
		return new ResponseEntity<String>("Video Deleted Successfully",HttpStatus.OK);
	}
}
