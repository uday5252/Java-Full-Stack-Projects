package com.project.zee.Video.Streaming.Platform.Controller;

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

import com.project.zee.Video.Streaming.Platform.Entity.VideoEntity;
import com.project.zee.Video.Streaming.Platform.Service.VideoService;
import com.project.zee.Video.Streaming.Platform.WrapperClass.WrapperClass;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;



@RestController
public class VideoController {
	
	@Autowired
	VideoService vs;
	
	@ApiResponse
    (
        responseCode = "201"
    )
    @Operation
    (
        summary = "Enter Video data",

        description = " Enter the details of the video by entering the genre , title , description and url of the video"
    )
	
	
	@PostMapping("/api/admin/videos")
	public ResponseEntity<String> UploadVideo(@RequestBody WrapperClass request) 
	{
	    vs.uploadvideo(request);
	    vs.sendUploadedVideosDataToVideosTopic(request);
	    return new ResponseEntity<>("Video uploaded successfully", HttpStatus.CREATED);
	}
	
	 @Operation
	    (
	        summary = "Get a specific video details",

	        description = "Enter the video id in the path to get the particular video "
	    )
	 
	@GetMapping("/api/videos/{videoId}")
	public ResponseEntity<VideoEntity> GetVideoDetails(@PathVariable int videoId)
	{
		VideoEntity video = vs.getVideoDetails(videoId);
		return new ResponseEntity<>(video, HttpStatus.OK);
		
	}
	 
	 @Operation
	    (
	        summary = "Get the list of all the videos ",

	        description = "Get the list of all the videos present in the database"
	    )
	
	@GetMapping("/api/videos")
	public ResponseEntity<List<VideoEntity>> GetVideos()
	{
		List<VideoEntity> videos = vs.getVideos();
		return new ResponseEntity<>(videos, HttpStatus.OK);
		
	}
	 
	 @Operation
	    (
	        summary = "Get all the videos of a particular genre ",

	        description = "Enter the genre id to get all the videos of that particular genre "
	    )
	
		
	@GetMapping("api/genres/{genreId}/videos")
	public ResponseEntity<List<VideoEntity>> getGenreVideos(@PathVariable("genreId") int genreid)
	{
		List<VideoEntity> videos = vs.getGenreVideos(genreid);
		return new ResponseEntity<>(videos, HttpStatus.OK);
	}
	
	 @Operation
	    (
	        summary = "Update video details",

	        description = "Update the specific video details by entering the updated data "
	    )
	 
	@PutMapping("/api/admin/videos/update/{videoId}")
	public ResponseEntity<String> UpdateVideo(@RequestBody WrapperClass request,@PathVariable int videoId) 
	{
	    vs.updateVideoDetails(request,videoId);
	    vs.sendEditedVideosDataToVideosTopic(videoId);
	    return new ResponseEntity<>("Video details updated successfully", HttpStatus.CREATED);
	}
	
	 @Operation
	    (
	        summary = "Delete a video ",

	        description = "Delete the specific video by entering the video id "
	    )
	 
	@DeleteMapping("/api/admin/videos/delete/{videoId}")
	public ResponseEntity<String> DeleteVideo(@PathVariable int videoId) 
	{
	    vs.deleteVideo(videoId);
	    vs.senddeletedVideosDataToVideosTopic(videoId);
	    return new ResponseEntity<>("Video Deleted successfully", HttpStatus.OK);
	}


}
