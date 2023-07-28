package com.xyz.demo.EndProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.xyz.demo.EndProject.Entity.UserEntity;
import com.xyz.demo.EndProject.Service.UserService;

//import org.springframework.stereotype.Controller;


@Controller
public class UserController 
{
  @Autowired
  UserService userService;
  
  @GetMapping("/")
  public String userMenu()
  {
	  return "home";
  }
  
  @GetMapping("/adduser")
  public String createUser(Model m)
  {
	 UserEntity ue= new UserEntity();
	 m.addAttribute("ue",ue);
	 return "user";
  }
  
  @PostMapping("/addsuccess")
  public String addUser(UserEntity ue,Model m)
  {
	 userService.createUserData(ue);
	 return "usersuccess";
  }
  
  @GetMapping("/list")
  public String userList(Model m)
  {
	  List<UserEntity> l=userService.getAllUsers();
	  m.addAttribute("list",l);
	  return "list";
  }
  
//  @GetMapping("/list/{id}")
//  public String userListbyId(Model m,@PathVariable("id") int id)
//  {
//	  //m.addAttribute("ue2",ue);
//	  UserEntity ue1=userService.userListbyIdData(id);
//	  m.addAttribute("ue1",ue1);
//	  return "userbyid";
//  }

  @PutMapping("/updateUser/{id}")
	public ResponseEntity<String> update(@PathVariable ("id") int id,@RequestBody UserEntity ue)
	{
		userService.updateUser(id,ue);
		return new ResponseEntity<>("updated", HttpStatus.CREATED);
	}
  
  @DeleteMapping("/user/{id}")
  public ResponseEntity<String> delete(@PathVariable ("id") int id)
  {
  	userService.deleteUser(id);
  	return new ResponseEntity<>("Deleted", HttpStatus.CREATED);
  }
}
