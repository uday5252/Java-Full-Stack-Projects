package com.abc.demo.Zee5.OTT.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.demo.Zee5.OTT.Project.Entity.Comment;
import com.abc.demo.Zee5.OTT.Project.Entity.User;
import com.abc.demo.Zee5.OTT.Project.Entity.Video;
import com.abc.demo.Zee5.OTT.Project.Service.CommentService;
import com.abc.demo.Zee5.OTT.Project.Service.UserService;
import com.abc.demo.Zee5.OTT.Project.Service.VideoService;

@Controller
public class CommentController {

	
	@Autowired
	VideoService vs;
	@Autowired
	UserService us;
	@Autowired
	CommentService cs;
	
	@GetMapping("/api/video/{videoId}/comment/{userId}")
	public String commentVideo(@PathVariable("videoId")int videoId,@PathVariable("userId")int userId,Model m)
	{
		User user=us.findUser(userId);
		Video video=vs.fetchVideoById(videoId);
		Comment comment =new Comment();
		comment.setUser(user);
		comment.setVideo(video);
		m.addAttribute("comment",comment);
//		System.out.println(comment.user.user_id);
		return "addCommentForm";
		
	}
	@PostMapping("/api/video/{videoId}/comment/")
	public String savecommentVideo(@PathVariable("videoId")int videoId,@RequestParam("user_id")int userId,Comment commentnew)
	{
		User user=us.findUser(userId);
		Video video=vs.fetchVideoById(videoId);
		cs.addComment(commentnew,user,video);
		
		return "redirect:/home";
		
	}
	@GetMapping("/api/video/deletecommentForm")
	public String deletecommentForm()
	{
		
		return "deleteCommentForm";
		
	}
	@PostMapping("/api/video/deletecomment")
	public String deletecomment(@RequestParam("comment_id")int comment_id)
	{
	
		
		cs.deletecomment(comment_id);
		return "redirect:/home";
		
	}
}
