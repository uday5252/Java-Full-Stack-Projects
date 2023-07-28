package com.example.demo.End.project.CONTROLLER;

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

import com.example.demo.End.project.ENTITY.VideoEntity;
import com.example.demo.End.project.REPOSITORY.VideoRepositoryInterface;
import com.example.demo.End.project.SERVICE.UserService;
import com.example.demo.End.project.SERVICE.VideoService;

@RestController
public class VideoController {
  
	@Autowired
	VideoService vs;
	@Autowired
	UserService us;
	@Autowired
	VideoRepositoryInterface vri;
	
	@PostMapping("/api/admin/{genreid}/{userid}/videos/upload")
	public ResponseEntity<String> uploadVideo(@RequestBody VideoEntity ve, @PathVariable("genreid") int gid, @PathVariable("userid") int userid)
	{
		vs.uploadvideo(ve,gid,userid);
		String message = "user with user ID " + ve.getUser().getId() + "uploaded the video" ;
		us.sendDataToVideoUpload(message);
		return new ResponseEntity<>("video uploaded Successfully", HttpStatus.OK);	
	}
	
	@GetMapping("/api/video/{videoid}")
	public ResponseEntity<VideoEntity> GetVideo(@PathVariable("videoid") int vid)
	{
		VideoEntity ve = vs.getVideo(vid);
		return new ResponseEntity<VideoEntity>(ve,HttpStatus.ACCEPTED);
	}
	@GetMapping("/api/video/Allvideos")
	public ResponseEntity<List<VideoEntity>> GetAllVideos()
	{
		List<VideoEntity> ve = vs.getAllVideo();
		return new ResponseEntity<List<VideoEntity>> (ve,HttpStatus.OK);
	}
	@GetMapping("/api/genres/{genreid}/videos")
	public ResponseEntity<List<VideoEntity>>GetGenreVideos(@PathVariable("genreid") int id)
	{
		List<VideoEntity> ve = vs.getgenreVideos(id);
		return new ResponseEntity<List<VideoEntity>> (ve,HttpStatus.OK);
	}
	@PutMapping("/api/videos/{videoid}/update")
	public ResponseEntity<String> UpdateVideo(@PathVariable("videoid") int id, @RequestBody VideoEntity ve)
	{
		vs.updateVideo(id,ve);
		String message = "user with user ID " + ve.getUser().getId() + "updated the video" ;
		us.sendDataToVideoUpload(message);
		return new ResponseEntity<>("Updated Succesfully",HttpStatus.OK);
	}
	@DeleteMapping("/api/admin/videos/{videoid}/delete")
	public ResponseEntity<String> DeleteVideo(@PathVariable("videoid") int id)
	{
		vs.deleteVideo(id);
		VideoEntity ve = vri.findById(id).get();
		String message = "Video with video id " + ve.getVideoid() +"the video" ;
		us.sendDataToVideoUpload(message);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}
	}
