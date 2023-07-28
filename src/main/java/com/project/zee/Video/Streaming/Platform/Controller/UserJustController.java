package com.project.zee.Video.Streaming.Platform.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.zee.Video.Streaming.Platform.Entity.UserEntity;

@Controller
public class UserJustController {
	

	
	 @GetMapping("/userRegistration")
	    public String showUserRegistrationPage(Model m) 
	 {
		 UserEntity user = new UserEntity();
		 m.addAttribute("user", user);
	        return "userRegistration";
	        
	 }
	 
	 @GetMapping("/userlogin")
	    public String showUserLoginPage(Model m) 
	 {
		 UserEntity user = new UserEntity();
		 m.addAttribute("user", user);
	        return "loginpage";
	        
	 }
	 
	
}
