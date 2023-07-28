package com.project.videostreamingproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.videostreamingproject.entity.LikeTabel;
import com.project.videostreamingproject.entity.User;
import com.project.videostreamingproject.entity.VideoTabel;
import com.project.videostreamingproject.repository.LikeRepo;

@Service
public class LikeService {
     
	
	@Autowired
	VideoService vt;
	
	@Autowired
	UserService us;
	
	@Autowired
	LikeRepo lr;
	@Autowired
	KafkaTemplate<String,String> kf;
	public void likevideo(int u,int v)
	{
		
		User user=us.find(u);
		VideoTabel video=vt.find(v);
	//	kf.send("USER-LIKES",user.getUserName() + " liked this video named " + video.getTitle());
	  LikeTabel	lk=new LikeTabel();
	  lk.setU(user);
	  lk.setV(video);
	  lr.save(lk);
		
	}
	
	public void dislike(int u,int v)
	{
		User user=us.find(u);
		VideoTabel video=vt.find(v);
		LikeTabel lt=lr.findByUAndV(user, video);
	//	kf.send("USER-LIKES",user.getUserName() + " disliked this video named " + video.getTitle());
		lr.delete(lt);
		
		
		
	}
}
