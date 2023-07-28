package com.example.VideoStreamingPlatform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.VideoStreamingPlatform.Entity.GenreEntity;
import com.example.VideoStreamingPlatform.Entity.VideoEntity;
import com.example.VideoStreamingPlatform.Service.GenreService;
import com.example.VideoStreamingPlatform.Service.VideoService;

import reactor.core.publisher.Mono;

@Controller
public class VideoController {
	
	@Autowired
	VideoService vs;
	
	@Autowired
	GenreService gs;
	
	//Add Video
	@GetMapping("/api/admin/videos")
	public String displayAddVideo(Model m) {
		VideoEntity video=new VideoEntity();
		m.addAttribute("video", video);
		return "AddVideoForm";
	}
	
	@PostMapping("/api/admin/add/videos")
	public String addVideo(@RequestParam("genre") int genre_id, VideoEntity video) {
		vs.addVideo(video, genre_id);
		return "redirect:/homepage";
	}
	
	// Get Video by video ID
	@GetMapping("/api/videos/")
	public String displayGetVideoByIdForm() {
		return "GetVideoByIdForm";
	}
	
	@PostMapping("api/videos/get")
	public String getVideoByVideoId(@RequestParam("video_id") int id, Model m) {
		VideoEntity ve = vs.getDetailsByVideoId(id);
		m.addAttribute("video", ve);
		return "DisplayVideo";
	}
	
	//Get All Videos
	@GetMapping("api/allVideos")
	public String getAllVideos(Model m) {
		List<VideoEntity> list = vs.getAllVideos();
		m.addAttribute("videos", list);
		return "video_list";
	}
	
	//Get Videos By Genre
	@GetMapping("api/genre/videos")
	public String displayGenreVideoForm() {
		return "GetVideoByGenreForm";
	}
	
	@PostMapping("/api/genre/videos/display")
	public String getVideosByGenre(@RequestParam("genre") int id, Model m) {
		GenreEntity genre=gs.getGenreById(id);
		List<VideoEntity> list=vs.getVideosByGenre(genre);
		m.addAttribute("genre", genre);
		m.addAttribute("video", list);
		return "DisplayVideoByGenre";
	}
	
	//Update Video
	@GetMapping("/updateVideo/{video_id}")
	public String displayUpdateVideo(@PathVariable("video_id") int video_id, Model m) {
		VideoEntity video = vs.getDetailsByVideoId(video_id);
		m.addAttribute("video", video);
		return "UpdateVideo";
	}
	@PostMapping("api/admin/videos/{id}")
	public String updateGenre(@ModelAttribute VideoEntity video, @PathVariable("id") int id) {
	    vs.updateVideo(video, id);
	    return "redirect:/homepage";
	}
	
	//Delete Video
	@GetMapping("/api/admin/delete/video")
	public String deleteVideoForm() {
		return "DeleteVideoForm";
	}
	
	@PostMapping("/api/admin/delete/video/delete")
	public String deleteVideo(@RequestParam("video_id") int video_id) {
		vs.deleteVideo(video_id);
		return "redirect:/homepage";
	}
	
	
	
	

}
