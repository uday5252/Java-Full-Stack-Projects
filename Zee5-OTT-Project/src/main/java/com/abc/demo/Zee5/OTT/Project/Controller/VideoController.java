package com.abc.demo.Zee5.OTT.Project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.demo.Zee5.OTT.Project.Entity.Video;
import com.abc.demo.Zee5.OTT.Project.Service.VideoService;

@Controller
public class VideoController {
	@Autowired
	VideoService vs;
	//add Video
	@GetMapping("/video/add/genre")
	public String addVideoRequirements(Model m)
	{
		
		Video video=new Video();
		
		m.addAttribute("video",video);
		return "addVideoForm";
	}
	
	@PostMapping("/video/add/genre")
	public String addVideForm(@RequestParam("genre_id") int genre_id,@RequestParam("user_id") int user_id,Video video)
	{
		
		vs.addVideo(video, genre_id, user_id);
		
		return "redirect:/home";
	}
	//list All videos
	@GetMapping("/api/videos")
	public String listAllVideos(Model m)
	{
		List<Video> video=vs.listAllVideos();
		
		m.addAttribute("video",video);
		return "displayAllVideos";
	}
	@GetMapping("/api/video/requirements")
	public String viewVideoRequirements(Model m)
	{
		Integer id = 0;
		m.addAttribute("id",id);
		return "viewVideoreq";
	}
	
	@PostMapping("/api/admin/videos/byvideoid")
	public String  viewVideo(@RequestParam("video_id")Integer id,Model m)
	{
		Video video=vs.fetchVideoById(id);
		m.addAttribute("video", video);
		return "displayVideo";
	}
	
	@GetMapping("/api/admin/videos/{videoId}")
	public String updateVideoRequirements(@PathVariable("videoId") int video_id,Model m)
	{
		Video video=vs.fetchVideoById(video_id);
		
		m.addAttribute("video",video);
		return "updateVideoForm";
	}
	@PostMapping("/api/admin/videos/{videoId}")
	public String updateVideoForm(@PathVariable("videoId") int video_id,Video video)
	{
		
		vs.updateVideo(video, video_id);
		
		return "redirect:/home";
	}
	
	@GetMapping("/api/admin/videos/delete/{videoId}")
	public String deleteGenre(@PathVariable("videoId")int videoId)
	{
		vs.deleteVideo(videoId);
		return "redirect:/home";
	}
	
	@GetMapping("/api/genres/requirements/videos")
	public String genrevideosrequirement()
	{
		return "genrerequirement";
	}
	@PostMapping("/api/genres/videosbygenre")
	public String showvideosbygenre(@RequestParam("genre_id")int genre_id,Model m)
	{
		List<Video> video=vs.getVideoByGenre(genre_id);
		m.addAttribute("video",video);
		return "displayAllVideos";
	}
	
	
}
