package com.abc.demo.Final.Project.implementing.all.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.Final.Project.implementing.all.Entity.LikeEntity;
import com.abc.demo.Final.Project.implementing.all.Entity.UserEntity;
import com.abc.demo.Final.Project.implementing.all.Entity.VideoEntity;
import com.abc.demo.Final.Project.implementing.all.REPOSITORY.LikeRepository;
import com.abc.demo.Final.Project.implementing.all.REPOSITORY.UserRepository;
import com.abc.demo.Final.Project.implementing.all.REPOSITORY.VideoRepository;

@Service
public class LikeService {

	
	@Autowired
	LikeRepository lr;
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	VideoRepository vr;
	
	@Autowired
	KafkaTemplate<Integer,String> kt;
	
	
	public void addLike(LikeEntity like,int uid,int vid) {
		UserEntity user = ur.findById(uid).get();
		like.setUser(user);
		
		VideoEntity video = vr.findById(vid).get();
		like.setVideo(video);
		
		lr.save(like);
		String message = "Video is liked by"+" "+user.getUsername();
		kt.send("UserLikes",message);
		
	}
	
}
