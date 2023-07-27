package com.abc.demo.Zee5.OTT.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.demo.Zee5.OTT.Project.Entity.User;
import com.abc.demo.Zee5.OTT.Project.Entity.Video;
import com.abc.demo.Zee5.OTT.Project.Service.LikeService;
import com.abc.demo.Zee5.OTT.Project.Service.UserService;
import com.abc.demo.Zee5.OTT.Project.Service.VideoService;

@Controller
public class LikeController {
	@Autowired
	VideoService vs;
	@Autowired
	UserService us;
	@Autowired
	LikeService ls;
	@GetMapping("/api/video/{videoId}/like/{userId}")
	public String likeVideoForm(@PathVariable("videoId")int videoId,Model m)
	{
		
		Video video=vs.fetchVideoById(videoId);
		m.addAttribute("video", video);
		return "likeBy";
		
	}
	@PostMapping("/api/video/{videoId}/like")
	public String likeVideo(@PathVariable("videoId")int videoId,@RequestParam("user_id")int userId)
	{
		User user=us.findUser(userId);
		Video video=vs.fetchVideoById(videoId);
		ls.addlike(user,video);
		
		return "redirect:/home";
		
	}
	
	@GetMapping("/api/video/{videoId}/unlike/{userId}")
	public String unlikeVideoForm(@PathVariable("videoId")int videoId,Model m)
	{
		
		Video video=vs.fetchVideoById(videoId);
		m.addAttribute("video", video);
		return "unlikeBy";
		
	}
	@GetMapping("/api/video/{videoId}/unlike")
	public String unlikeVideo(@PathVariable("videoId")int videoId,@RequestParam("userId")int userId)
	{
		User user=us.findUser(userId);
		Video video=vs.fetchVideoById(videoId);
		ls.deletelike(user,video);
		
		return "redirect:/home";
		
	}
}
