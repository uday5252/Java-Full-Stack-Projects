package com.project.ZeeWebApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ZeeWebApplication.Entity.VideoEntity;
import com.project.ZeeWebApplication.RapperClass.RapperClass;
import com.project.ZeeWebApplication.Service.VideoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
		name = "Video API controller",
		description = "This contains all the End points od video Application"
		)

@RestController
public class VideoController {

	@Autowired
	VideoService vs;
	
	@Autowired
	ResourceLoader loader;
	
	@PostMapping("/api/admin/uploadvideo")
	@ApiResponse
	(
		responseCode = "201"
	)
	@Operation
	(
		summary = "Insert Genre data",
		description = "Insert the Genre data form user side"
	)
	public ResponseEntity<String> UplaodaVideo(@RequestBody RapperClass rc) 
	{
		vs.UploadTheVideo(rc);
		
		int uid = rc.getUserid();
		String  gname = rc.getGenrename();
		
		String link = rc.getUrl();
		
		vs.sendDataToVideoTopic(uid,gname,link);
		
		return new ResponseEntity<>("Video Uploaded Sucessfully",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/videos/{videoid}")
	@ApiResponse
	(
		responseCode = "200"
	)
	@Operation
	(
		summary = "Read specific video data",
		description = "read the specific video data form user side"
	)
	public ResponseEntity<VideoEntity> GetaParticularVideo(@PathVariable("videoid") int vid)
	{
		VideoEntity ve = vs.getTheVideoInformation(vid);
		
		return new ResponseEntity<>(ve ,HttpStatus.OK);
	}
	
	
	@GetMapping("/api/allvideos")
	@ApiResponse
	(
		responseCode = "200"
	)
	@Operation
	(
		summary = "Read all videos data",
		description = "read all videos data form user side"
	)
	public ResponseEntity<List<VideoEntity>> GetaParticularVideo()
	{
		List<VideoEntity> listve = vs.getAllVideoInformation();
		
		return new ResponseEntity<>(listve ,HttpStatus.OK);
	}
	
	@GetMapping("/api/genrevideos/{genreid}")
	@ApiResponse
	(
		responseCode = "200"
	)
	@Operation
	(
		summary = "Read all genre videos data",
		description = "read all genre videos data form user side"
	)
	public ResponseEntity<List<VideoEntity>> GetVideosByGenre(@PathVariable("genreid") int gid)
	{
		List<VideoEntity> listall = vs.getGenreVideos(gid);
		
		return new ResponseEntity<>(listall,HttpStatus.OK);
	}
	
	@PutMapping("/api/admin/video/update/{videoid}")
	@ApiResponse
	(
		responseCode = "200"
	)
	@Operation
	(
		summary = "Update video data",
		description = "update the video data form user side"
	)
	public ResponseEntity<String> UpdateVideoInformation(@RequestBody RapperClass rp,@PathVariable("videoid") int vid)
	{
		vs.UpdateInformation(rp,vid);
		
		return new ResponseEntity<>("Updated Video information sucessfully",HttpStatus.OK);
	}
	
	@DeleteMapping("/api/admin/video/delete/{videoid}")
	@ApiResponse
	(
		responseCode = "200"
	)
	@Operation
	(
		summary = "Delete Video data",
		description = "delete the video data form user side"
	)
	public ResponseEntity<String> DeleteVideoInformation(@PathVariable("videoid") int vid)
	{
		vs.DeletetheVideo(vid);
		
		return new ResponseEntity<>("deleted the video information sucessfully",HttpStatus.OK);
	}
}
