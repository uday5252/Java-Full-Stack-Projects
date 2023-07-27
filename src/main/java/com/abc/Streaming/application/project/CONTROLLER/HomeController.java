package com.abc.Streaming.application.project.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.Streaming.application.project.ENTITIES.CommentEntity;
import com.abc.Streaming.application.project.ENTITIES.GenreEntity;
import com.abc.Streaming.application.project.ENTITIES.LikeEntity;
import com.abc.Streaming.application.project.ENTITIES.UserEntity;
import com.abc.Streaming.application.project.ENTITIES.VideoEntity;
import com.abc.Streaming.application.project.KAFKA.ProducerClass;
import com.abc.Streaming.application.project.SERVICE.CommentService;
import com.abc.Streaming.application.project.SERVICE.GenreService;
import com.abc.Streaming.application.project.SERVICE.LikeService;
import com.abc.Streaming.application.project.SERVICE.UserService;
import com.abc.Streaming.application.project.SERVICE.VideoService;

import reactor.core.publisher.Mono;



@RestController
public class HomeController 
{
	@Autowired
	GenreService gs;
	
	@Autowired
	VideoService vs;
	
	@Autowired
	UserService us;
	
	@PostMapping("/api/adduser")
	public ResponseEntity<String> adduser(@RequestBody UserEntity ue)
	{
		us.addUser(ue);
		return new ResponseEntity<>("User added Succesfully",HttpStatus.ACCEPTED);
		
	}
	
	
	
	
	
	
	
	@PostMapping("/api/user/genres")
	public ResponseEntity<String> addgenre(@RequestBody GenreEntity ge)
	{
//		System.out.println(ge.getName());
		gs.addgenre(ge);
		return new ResponseEntity<>("genre added Succesfully",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/read/genres")
	public ResponseEntity<List<GenreEntity>> readGenre()
	{
		List<GenreEntity> list = gs.readgenre();
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/api/user/genres/{genreId}")
	public ResponseEntity<String> updategenre(@RequestBody GenreEntity ge, @PathVariable("genreId") int gid)
	{
		
		gs.updategenre(ge,gid);
		p.sendmessage4();
		return new ResponseEntity<>("genre updated Succesfully",HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/user/genres/delete/{genreId}")
	public ResponseEntity<String> deletegenre(@PathVariable("genreId") int gid)
	{
		
		gs.deletegenre(gid);
		return new ResponseEntity<>("genre deleted Succesfully",HttpStatus.ACCEPTED);
	}
	

	

	
	
	
	
	@Autowired
	ProducerClass p;
	
	@PostMapping("api/admin/videos")
	public ResponseEntity<String> addVideo(@RequestBody VideoEntity ve)
	{
		System.out.println(ve);
		vs.addVideo(ve);
		p.sendmessage();
		
		
		return new ResponseEntity<>("video added Succesfully",HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/api/videos/{videoId}")
	public ResponseEntity<VideoEntity> readvideo(@PathVariable("videoId") int vid)
	{
	 
	  VideoEntity video=vs.readvideo(vid);
	  return new ResponseEntity<VideoEntity>(video,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/read/videos")
	public ResponseEntity<List<VideoEntity>> readVideos()
	{
		List<VideoEntity> list = vs.readallvideo();
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("api/genres/{genreId}/videos")
	public ResponseEntity<List<VideoEntity>> readVideosbyGenre(@PathVariable("genreId") int gid)
	{
		List<VideoEntity> list = vs.readgenrevideo(gid);
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/api/admin/videos/{videoId}")
	public ResponseEntity<String> updatevideo(@RequestBody VideoEntity ve, @PathVariable("videoId") int vid)
	{
		
		vs.updatevideo(ve,vid);
		return new ResponseEntity<>("video updated Succesfully",HttpStatus.ACCEPTED);
	}
	@DeleteMapping("api/admin/videos/{videoId}")
	public ResponseEntity<String> deletevideo(@PathVariable("videoId") int vid)
	{
		
		vs.deletevideo(vid);
		return new ResponseEntity<>("video deleted Succesfully",HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/videos/{vname}", produces = "video/mp4")
	public Mono<Resource> displayvideo(@PathVariable("vname") String videoname)
	{
		return vs.streamvideo(videoname);
		
	}
	
	
	
	
	
	@Autowired
	LikeService ls;
	
	@PostMapping("/api/user/like")
	public ResponseEntity<LikeEntity> addlike(@RequestBody LikeEntity le)
	{
		System.out.println(le);
		LikeEntity saved = ls.addlike(le);
		p.sendmessage2();
//		System.out.println(le.getUserId());
		return new ResponseEntity<LikeEntity>(saved, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/api/user/unlike/{userId}")
	public ResponseEntity<String> unlike(@PathVariable("userId") int uid)
	{
		
		ls.Unlike(uid);
//		System.out.println(le.getUserId());
		return new ResponseEntity<String>("unliked successfully", HttpStatus.ACCEPTED);
		
	}
	
	
	
	
	@Autowired
	CommentService cs;
	
	@PostMapping("/api/user/comment")
	public ResponseEntity<String> addComment(@RequestBody CommentEntity ce)
	{
		cs.addcomment(ce);
		p.sendmessage3();
		return new ResponseEntity<String>("comment successfully added the video",HttpStatus.ACCEPTED);
		
	}
	@GetMapping("/api/user/uncomment/{userId}")
	public ResponseEntity<String> uncomment(@PathVariable("userId") int uid)
	{
		
		cs.uncomment(uid);
//		System.out.println(le.getUserId());
		return new ResponseEntity<String>("unlcommented successfully", HttpStatus.ACCEPTED);
		
	}
	
	
	
}
