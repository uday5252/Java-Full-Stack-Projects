package com.abc.demo.Final.Project.implementing.all.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.demo.Final.Project.implementing.all.SERVICE.PlayerService;

@Controller
public class PlayerController {

	
	@Autowired
	PlayerService ps;
	
	@GetMapping("/play/{vid}")
	public String videoPlay(@PathVariable("vid") int vid, Model m) {
		
		String s = ps.getVideo(vid);
		m.addAttribute("s", s);
		return "display";
	}
}
