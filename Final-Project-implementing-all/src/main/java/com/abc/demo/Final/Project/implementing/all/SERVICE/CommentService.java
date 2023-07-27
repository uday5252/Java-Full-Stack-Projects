package com.abc.demo.Final.Project.implementing.all.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.Final.Project.implementing.all.Entity.CommentEntity;
import com.abc.demo.Final.Project.implementing.all.Entity.UserEntity;
import com.abc.demo.Final.Project.implementing.all.Entity.VideoEntity;
import com.abc.demo.Final.Project.implementing.all.REPOSITORY.CommentRepository;
import com.abc.demo.Final.Project.implementing.all.REPOSITORY.UserRepository;
import com.abc.demo.Final.Project.implementing.all.REPOSITORY.VideoRepository;

@Service
public class CommentService {

	
	@Autowired
	CommentRepository cr;
	

	@Autowired
	UserRepository ur;
	
	@Autowired
	VideoRepository vr;
	
	@Autowired
	KafkaTemplate<Integer,String> kt;
	

	
	
	public void addComment(CommentEntity ce,int uid,int vid) {
		UserEntity user = ur.findById(uid).get();
		ce.setUsercom(user);
		
		VideoEntity video = vr.findById(vid).get();
		ce.setVideocom(video);
		
		cr.save(ce);
		String s =ce.getComment();
		//String comment = cr.findByUsercomAndVideocom(user, video);
//		kt.send("UserComments",comment);
		System.out.println(s);
		kt.send("UserComments",s);
		
	}
	

	
}
