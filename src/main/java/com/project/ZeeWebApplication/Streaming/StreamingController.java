package com.project.ZeeWebApplication.Streaming;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.ZeeWebApplication.Entity.VideoEntity;
import com.project.ZeeWebApplication.Repository.VideoRepositoryInterface;
import com.project.ZeeWebApplication.Service.VideoService;


@Controller
public class StreamingController {
	
	@Autowired
	VideoService videoService;
	
	@Autowired
	VideoRepositoryInterface vri;
	
	@GetMapping("/api/videostreaming/{videoid}")
	public String GetaParticularVideotoStream(@PathVariable("videoid") int vid,Model m)
	{
		
		String path = videoService.getTheVideoPath(vid);
		
			
		m.addAttribute("key", path);
		
		return "onevideoview";
	}
	
	@GetMapping("/api/genre/videostreaming/{genreid}")
	public String getAllGenreVideos(@PathVariable("genreid") int  gid,Model m) {
		
		List<VideoEntity> paths = videoService.getAllGenreVideoPaths(gid);
		
		m.addAttribute("videos", paths);
		
		return "multiplevideos";	
	}
	
	@GetMapping("/api/allvideos/streaming")
	public String getAllVideosfromDB(Model m)
	{
		List<VideoEntity> list = videoService.getallvideosofDB();
		
		m.addAttribute("videos", list);
		
		return "multiplevideos";
	}
}
