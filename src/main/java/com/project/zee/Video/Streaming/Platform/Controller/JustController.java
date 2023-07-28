package com.project.zee.Video.Streaming.Platform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.zee.Video.Streaming.Platform.Entity.VideoEntity;
import com.project.zee.Video.Streaming.Platform.Service.StreamingService;

@Controller
public class JustController {
	
	@Autowired
	StreamingService ss;
	
	
	//to stream the video based on the video name entered in the path
	
//	@GetMapping(value="/video/{videoname}", produces = "video/mp4")
//	public  Object video(@PathVariable("videoname") String vname,Model m)
//	{
//		System.out.println(vname);
//
//		m.addAttribute("v",vname);
//		
//		return vs.readVideo(vname);
//		
//	}
	
	//to stream based on the videoid
	
	@GetMapping(value="/video/{videoId}", produces = "video/mp4")
	public  Object StreamSpecificVideos(@PathVariable int videoId,Model m)
	{
//		System.out.println(vname);
		
		String url = ss.PlaySpecificVideo(videoId);

		m.addAttribute("v",url);
		
		return "specificvideo";
		
	}
	
	@GetMapping("/video/PlayAll")
	public Object PLayAllVideos(Model m)
	{
		List<VideoEntity> videos = ss.PlayAllVideos();
		m.addAttribute("v",videos);
		return "Playall";
	}
	
	@GetMapping("video/genre/{genreId}/PlayAll")
	public Object PlayGenreVideos(@PathVariable int genreId ,Model m)
	{
		List<VideoEntity> genrevideos = ss.GenreVideos(genreId);
		m.addAttribute("v",genrevideos);
		return "Playall";
	}

}

