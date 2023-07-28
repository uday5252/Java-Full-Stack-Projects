package com.example.VideoStreamingPlatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.VideoStreamingPlatform.Entity.UserEntity;
import com.example.VideoStreamingPlatform.Service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService us;
	
	@GetMapping("homepage")
	public String displayHomePage() {
		return "homepage";
	}
	
	@GetMapping("/api/user")
	public String displayUserForm(Model m) {
		UserEntity user = new UserEntity();
		m.addAttribute("user",user);
		return "UserForm";
		
	}
	
	@PostMapping("/add/user")
	public String addUser(UserEntity user) {
		us.addUser(user);
		return "redirect:/homepage";
	}
}
