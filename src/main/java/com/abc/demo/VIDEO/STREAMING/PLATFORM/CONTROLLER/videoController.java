package com.abc.demo.VIDEO.STREAMING.PLATFORM.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.VideoEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.commentEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.genreEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.likeEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.userEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE.commentService;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE.genreService;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE.likeService;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE.userService;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE.videoService;

@Controller
public class videoController {
	
	
	@Autowired
	videoService vs;
	
	@Autowired
	userService us;
	
	@Autowired
	genreService gs;
	
	
	@Autowired
	likeService ls;
	
	@Autowired
	commentService cs;
	
	
	
	@Autowired    //offset,message
	KafkaTemplate<String,String> kt;
	
	
	
	@PostMapping("/addvideo/{genreId}/{userId}")
	public ResponseEntity<String> addVideo(@PathVariable("genreId") int genreId,@PathVariable("userId") int userId,@RequestBody VideoEntity ve) {
		
		
		genreEntity genre= gs.getGenreById(genreId);
		
		
		userEntity user= us.getUserById(userId);
		
		
		VideoEntity video=new VideoEntity();
		
		video.setGenreID(genre);
		
		video.setUserID(user);
		
		video.setDescription(ve.getDescription());
		
		video.setTitle(ve.getTitle());
		
		video.setUrl(ve.getUrl());
		
		vs.addvideobyUser(video);
		
		String message=user.getUsername()+" has uploaded "+video.getTitle()+" video"+" and the url of the video is "+video.getUrl();
		
		
		kt.send("video_uploads", message);
		
	 return new ResponseEntity<>("User added video successfully",HttpStatus.OK);
		
	}
	
	
//	GET /api/videos/{videoId}`: Get details of a specific video.
	
	@GetMapping("/videos/{videoId}")
	public String getVideo(@PathVariable("videoId")int id,Model m) {
		
		 VideoEntity videoDetails=vs.getVideoById(id);
		 
		    List<likeEntity> likes = ls.getLikesByVideoId(id);
		    List<commentEntity> comments = cs.getCommentsByVideoId(id);

		    m.addAttribute("video", videoDetails);
		    m.addAttribute("likes", likes);
		    m.addAttribute("comments", comments);
		    m.addAttribute("numComments", comments.size());

		 
		    m.addAttribute("video",videoDetails);
		
		 return "playVideo";
		
		
//		 return new ResponseEntity<>(videoDetails,HttpStatus.OK);
		
	}
	
//	GET /api/videos`: Get a list of all videos.
	
	@GetMapping("/videos")
	  public String getVideos(Model m) {
		
		List<VideoEntity>videos=vs.getAllvideos();
		
		m.addAttribute("video", videos);
		
		return "playVideo";
		
		
	}
	
	
//	 `GET /api/genres/{genreId}/videos`: Get a list of videos within a specific genre
	@GetMapping("/videos/genres/{genreId}")
	public String getVideosByGenre(@PathVariable("genreId")int genreId,Model m) {
		
		List<VideoEntity>Genrevideos=vs.getVideosbyGenreId(genreId);
		
		m.addAttribute("video", Genrevideos);
		
		return "playVideo";
		
	}
	
//	DELETE /api/admin/videos/{videoId}`: Delete a video (Admin only)
	@DeleteMapping("/videos/{videoId}")
	public ResponseEntity<String> deleteVideo(@PathVariable("videoId")int videoId) {
		
		
		VideoEntity video=vs.getVideoById(videoId);
		
		userEntity user=video.getUserID();
		
		vs.deleteVideoById(videoId);
		
        String message=user.getUsername()+" has deleted his "+video.getTitle()+ " video and the url of the video is "+video.getUrl();
		
		
		kt.send("video_uploads", message);
		
		return new ResponseEntity<>("video deleted successfully",HttpStatus.OK);	
	}
	
	
//	`PUT /api/admin/videos/{videoId}`: Update video details (Admin only)
	@PutMapping("/videos/{videoId}")
	public ResponseEntity<String> updateDetails(@PathVariable("videoId")int videoId,@RequestBody VideoEntity ve) {
		
        VideoEntity video=vs.getVideoById(videoId);
		
		userEntity user=video.getUserID();
		

        String message=user.getUsername()+" has updated his "+video.getTitle()+ " video details";
		
		
		kt.send("video_uploads", message);
		
		
		vs.updateVideoDetails(videoId,ve);
		
		return new ResponseEntity<>("video details updated successfully",HttpStatus.OK);		
		
		
	}
	

}

