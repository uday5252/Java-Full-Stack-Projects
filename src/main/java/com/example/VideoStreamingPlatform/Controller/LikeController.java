package com.example.VideoStreamingPlatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.VideoStreamingPlatform.Entity.LikeEntity;
import com.example.VideoStreamingPlatform.Service.LikeService;

@Controller
public class LikeController {
	
	@Autowired
	LikeService ls;
	
	@GetMapping("/api/add/likes")
	public String displayLikeForm(Model m) {
		LikeEntity like = new LikeEntity();
		m.addAttribute("like", like);
		return "LikeForm";
	}
	
	@PostMapping("/api/add/likes/form")
	public String addLike(@RequestParam("video") int video, @RequestParam("user") int user, LikeEntity like) {
		ls.addLike(like, video, user);
		return "redirect:/homepage";
	}
	
	//Delete Like
	@GetMapping("/api/remove/likes")
	public String removeLikeForm() {
		return "RemoveLikeForm";
	}
	
	@PostMapping("/api/remove/likes/user")
	public String removeLikes(@RequestParam("video") int video, @RequestParam("user") int user) {
		ls.removeLike(video, user);
		return "redirect:/homepage";
	}
	
}
