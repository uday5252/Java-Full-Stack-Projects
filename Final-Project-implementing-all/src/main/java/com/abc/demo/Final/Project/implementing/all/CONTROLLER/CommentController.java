package com.abc.demo.Final.Project.implementing.all.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.Final.Project.implementing.all.Entity.CommentEntity;
import com.abc.demo.Final.Project.implementing.all.SERVICE.CommentService;

@RestController
public class CommentController {

	
	@Autowired
	CommentService cs;
	
	@PostMapping("/comment/user/{uid}/video/{vid}")
	public void commentAdd(@RequestBody CommentEntity cen,@PathVariable("uid") int uid,@PathVariable("vid") int vid) {
		cs.addComment(cen, uid, vid);
	}
}
