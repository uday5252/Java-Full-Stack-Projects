package com.project.zee.Video.Streaming.Platform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.zee.Video.Streaming.Platform.Entity.LikesEntity;
import com.project.zee.Video.Streaming.Platform.Entity.UserEntity;
import com.project.zee.Video.Streaming.Platform.Entity.VideoEntity;
import com.project.zee.Video.Streaming.Platform.Repository.LikesInterface;
import com.project.zee.Video.Streaming.Platform.Repository.UserInterface;
import com.project.zee.Video.Streaming.Platform.Repository.VideoInterface;

import jakarta.transaction.Transactional;

@Service 
public class LikeService {
	
	@Autowired
	LikesInterface li;
	
	@Autowired
	VideoInterface vi;
	
	@Autowired 
	UserInterface ui;

	@Autowired
	KafkaTemplate<Integer,String> kt;
	
	public ResponseEntity<String> LikeAVideo(int videoId,int userId)
	{
		
		if(li.findByVideoIdAndUserId(videoId,userId)!=null)
		{
			return new ResponseEntity<> ("user already liked the video ",HttpStatus.OK);
		}
		else 
		{
			VideoEntity video = vi.findById(videoId).get();
			UserEntity user = ui.findById(userId).get();
			
			LikesEntity le = new LikesEntity();
			le.setUser(user);
			le.setVideo(video);
			li.save(le);
			return new ResponseEntity<> ("user liked the video ",HttpStatus.CREATED);
		}
	}
	
	@Transactional
	public void deleteLike(int videoId,int userId)
	{
		li.deleteByUserIdAndVideoId(userId,videoId);
	}

	

	public void sendLikesUpdateToLikesTopic(int userid,int videoid)
	{	
		String likesUpdate = "User " + userid + " Liked " + "video "+ videoid;
		kt.send("likesDataTopic",likesUpdate);
		
	}
	
	public void sendUnlikesUpdateToLikesTopic(int userid, int videoid)
	{
		
		
			String UnlikesUpdate = "User " + userid + " UnLiked " + "video "+ videoid;
			kt.send("likesDataTopic",UnlikesUpdate);
		
		
	}
	
}
