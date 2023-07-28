package com.example.endtoendproject.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.endtoendproject.ENTITY.Video;
import com.example.endtoendproject.SERVICE.VideoService;

@Controller
public class displayVideoController {
	@Autowired
	VideoService vs;
	//frontend
	@GetMapping("/video/{videoId}")
	public String displayVideoDetails(@PathVariable("videoId") int vid,Model m) {
		Video video = vs.getVideoById(vid);
		String title = video.getTitle();
		//System.out.println(title);
		m.addAttribute("vid", video);
		return "singleVideoDisplay";
	}
}
