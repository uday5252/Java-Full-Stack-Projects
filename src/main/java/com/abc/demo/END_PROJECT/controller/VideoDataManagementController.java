package com.abc.demo.END_PROJECT.controller;

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

import com.abc.demo.END_PROJECT.Apache_Kafka.KafkaService;
import com.abc.demo.END_PROJECT.entity.VideoEntity;
import com.abc.demo.END_PROJECT.service.VideoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class VideoDataManagementController {

	@Autowired
	VideoService vs;
	
	@Autowired
	KafkaService ks;
	@Operation
	(
		summary="Add video",
		description="This API Endpoint is used for creating video details"
	)
	@PostMapping("/api/admin/{genreid}/{userid}/video/upload")
	public ResponseEntity<String> uploadVideo(@RequestBody VideoEntity video,@PathVariable("userid") int uid,@PathVariable("genreid") int gid){
		vs.uploadVideo(video,uid,gid);
		String message = "video with url "+video.getUrl()+" is uploaded by "+video.getUploaded_by().getUsername();
		ks.sendDataToVideo_UploadsTopic(message);
		
		return new ResponseEntity<>("Video has been uploaded successfully",HttpStatus.CREATED);
	}
	@Operation
	(
		summary="Get video by ID",
		description="This API Endpoint is used for getting specific video"
	)
	
	@GetMapping("/api/videos/{videoid}/read")
	public ResponseEntity<VideoEntity> readVideo(@PathVariable("videoid") int vid){
		VideoEntity ve = vs.readVideo(vid);
		return new ResponseEntity<VideoEntity>(ve,HttpStatus.OK);
		
	}
	
	@Operation
	(
			summary="Get all videos",
			description="This API Endpoint is used for getting all videos"
		)
	
	
	@GetMapping("/api/videos/readAll")
	public ResponseEntity<List<VideoEntity>> readAllVideos(){
		List<VideoEntity> ls = vs.readAllVideos();
		return new ResponseEntity<List<VideoEntity>>(ls,HttpStatus.OK);
	}
	
	
	@Operation
	(
		summary="Get Videos by genre",
		description="This API Endpoint is used for getting all videos of specific genre"
	)
	@GetMapping("/api/genres/{genreid}/videos/read")
	public ResponseEntity<List<VideoEntity>> readVideoByGenre(@PathVariable("genreid") int gid){
		List<VideoEntity> ls = vs.readVideoByGenre(gid);
		return new ResponseEntity<List<VideoEntity>>(ls,HttpStatus.OK);
	}
	@Operation
	(
		summary="Update video",
		description="This API Endpoint is used for updating video details"
	)
	
	
	
	@PutMapping("/api/admin/videos/{videoid}/update")
	public ResponseEntity<String> updateVideo(@RequestBody VideoEntity video, @PathVariable("videoid") int vid){
		vs.updateVideo(video,vid);
		return new ResponseEntity<String>("video details has been updated succesfully",HttpStatus.ACCEPTED);
	}
	

	
	@Operation
	(
		summary="Delete video",
		description="This API Endpoint is used for deleting video "
	)
	@DeleteMapping("/api/admin/videos/{videoid}")
	public ResponseEntity<String> deleteVideo(@PathVariable("videoid") int vid){
		
		VideoEntity ve = vs.deleteVideo(vid);
		String message = "Admin deleted the video with the url " +ve.getUrl() +" and with title " +ve.getTitle();
		ks.sendDataToVideo_UploadsTopic(message);
		return new ResponseEntity<>("Video has been deleted successfully ",HttpStatus.OK);
	}
	
	
}
