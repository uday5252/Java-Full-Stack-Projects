 package com.abc.demo.END_PROJECT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.demo.END_PROJECT.entity.UserEntity;
import com.abc.demo.END_PROJECT.entity.VideoEntity;
import com.abc.demo.END_PROJECT.repository.VideoRepository;
import com.abc.demo.END_PROJECT.service.VideoService;

@Controller
public class StreamingController {

	@Autowired
	VideoService vs;
	@Autowired
	VideoRepository vr;
	
	
	@GetMapping("/add/User")
	public String createUser(Model m) {
		UserEntity ue = new UserEntity();
		m.addAttribute("e1",ue);
		return "createUserForm";
	}
	
	@GetMapping("/api/")
	public String displayHome(){
		
		return "home";
	}
	
	
	@GetMapping("/api/videos/{videoid}/get")
	public String readVideo(@PathVariable("videoid") int vid,Model m){
		VideoEntity ve = vs.readVideo(vid);
		String path = ve.getUrl();
		m.addAttribute("key", path);
		return "view";
		
	}
	
	@GetMapping("/api/videos/getAll")
	public String readAllVideos(Model m){
		List<VideoEntity> ls = vs.readAllVideos();
		m.addAttribute("allvid", ls);
		return "viewall";
	}
	
	@GetMapping("/api/genres/{genreid}/videos/get")
	public String readVideoByGenre(@PathVariable("genreid") int gid,Model m){
		List<VideoEntity> ls = vs.readVideoByGenre(gid);
		m.addAttribute("vidbygenre", ls);
		return "viewbygenre";
	}
}
