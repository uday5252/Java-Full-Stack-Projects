package com.project.videostreamingproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.videostreamingproject.entity.Comments;
import com.project.videostreamingproject.entity.User;
import com.project.videostreamingproject.entity.VideoTabel;
import com.project.videostreamingproject.repository.CommentRepo;

@Service
public class CommentService {
     
	
	@Autowired
	CommentRepo cr;
	@Autowired
	KafkaTemplate<String,String> kf;
	public void setcomment(User uid,VideoTabel vid,String Comment)
	{
		Comments c=new Comments(uid,vid,Comment);
	//	kf.send("USER-COMMENTS",vid.getTitle() + " --------" + Comment+"by --------"+uid.getUserName());
		cr.save(c);
	}
	
	public void deletecomment(User uid,VideoTabel vid)
	{
		Comments c=cr.findByUAndV(uid, vid);
		cr.delete(c);
	}
}
