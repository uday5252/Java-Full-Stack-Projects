package com.project.ZeeWebApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.ZeeWebApplication.Entity.CommentEntity;
import com.project.ZeeWebApplication.Entity.UserEntity;
import com.project.ZeeWebApplication.Entity.VideoEntity;
import com.project.ZeeWebApplication.Repository.CommentRepositoryInterface;
import com.project.ZeeWebApplication.Repository.UserRepositoryInterface;
import com.project.ZeeWebApplication.Repository.VideoRepositoryInterface;

import jakarta.transaction.Transactional;

@Service
public class CommentService {

	@Autowired
	CommentRepositoryInterface cri;
	
	@Autowired
	UserRepositoryInterface uri;
	
	@Autowired
	VideoRepositoryInterface vri;
	
	@Autowired
	KafkaTemplate<Integer, String> kt;

	public void CommentedThevideoBy(CommentEntity centity, int uid, int vid) {
		
		UserEntity ue = uri.findById(uid).get();
		centity.setUser(ue);
		
		VideoEntity ve = vri.findById(vid).get();
		centity.setVideo(ve);
		
		cri.save(centity);
		
	}

	@Transactional
	public void deleteTheCommentedvideoBy(int uid, int vid) {
		
		cri.deleteByUserIdAndVideoId(uid,vid);
		
	}

	public void sendDataToCommentTopic(int uid, int vid) {
		
		String message = Integer.toString(uid) + " Id user commented on the videoID:- " + Integer.toString(vid);
		
		kt.send("user_comments",message);
		
	}

	
}
