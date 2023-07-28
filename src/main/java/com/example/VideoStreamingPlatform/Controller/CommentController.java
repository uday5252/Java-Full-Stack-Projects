package com.example.VideoStreamingPlatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.VideoStreamingPlatform.Entity.CommentEntity;
import com.example.VideoStreamingPlatform.Service.CommentService;

@Controller
public class CommentController {
	
	@Autowired
	CommentService cs;
	
	@GetMapping("/api/add/Comment/form")
	public String displayCommentForm(Model m) {
		CommentEntity comment = new CommentEntity();
		m.addAttribute("comment", comment);
		return "DisplayCommentForm";
	}
	
	@PostMapping("/api/add/Comment")
	public String addComment(CommentEntity comment, @RequestParam("video") int video_id, @RequestParam("user") int user_id) {
		cs.addComment(comment, video_id, user_id);
		return "redirect:/homepage";
	}
	
	//Remove COmment
	@GetMapping("/api/remove/Comment/form")
	public String removeCommentForm() {
		return "RemoveCommentForm";
	}
	
	@PostMapping("/api/remove/Comment/user")
	public String removeLikes(@RequestParam("video") int video, @RequestParam("user") int user) {
		cs.removeComment(video, user);
		return "redirect:/homepage";
	}
	
}
