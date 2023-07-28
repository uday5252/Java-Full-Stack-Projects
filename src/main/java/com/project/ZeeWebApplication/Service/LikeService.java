package com.project.ZeeWebApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.ZeeWebApplication.Entity.LikeEntity;
import com.project.ZeeWebApplication.Entity.UserEntity;
import com.project.ZeeWebApplication.Entity.VideoEntity;
import com.project.ZeeWebApplication.Repository.LikeRepositoryInterface;
import com.project.ZeeWebApplication.Repository.UserRepositoryInterface;
import com.project.ZeeWebApplication.Repository.VideoRepositoryInterface;

@Service
public class LikeService {

	@Autowired
	LikeRepositoryInterface lri;
	
	@Autowired
	UserRepositoryInterface uri;
	
	@Autowired
	VideoRepositoryInterface vri;
	
	@Autowired
	KafkaTemplate<Integer, String> kt;

	public void userLikedTheVideo(int uid, int vid) {
		
		LikeEntity le = new LikeEntity();
		
		UserEntity ue = uri.findById(uid).get();
		le.setUser(ue);
		
		VideoEntity ve = vri.findById(vid).get();
		le.setVideo(ve);
		
		lri.save(le);
	}

	public boolean HasLiked(int uid, int vid) {
		
		return lri.findByUserIdAndVideoId(uid,vid)!=null;
	}

	public void userUnLikedTheVideo(int uid, int vid) {
		
		LikeEntity le =  lri.findByUserIdAndVideoId(uid, vid);
		
		int videoid = le.getLikeid();
		
		lri.deleteById(videoid);
		
	}

	public void sendDataToLikeTopic(int uid, int vid) {
		
		String message = Integer.toString(uid) + " id user liked the " + Integer.toString(vid) + " id video ";
		
		kt.send("user_likes",message);
		
	}

}
