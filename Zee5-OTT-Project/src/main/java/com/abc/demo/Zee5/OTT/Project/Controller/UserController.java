package com.abc.demo.Zee5.OTT.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.abc.demo.Zee5.OTT.Project.Entity.User;
import com.abc.demo.Zee5.OTT.Project.Service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService us;
	@GetMapping("/home")
	public String homepage()
	{
		return "homepage";
	}
	@GetMapping("/api/createuser")
	public String displayUserform(Model m)
	{
		User user=new User();
		m.addAttribute("user",user);
		return "displayUser";
		
	}
	@PostMapping("/api/adduser")
	public String createUser(User user)
	{
		us.createUser(user);
		return "redirect:/home";
	}
}
