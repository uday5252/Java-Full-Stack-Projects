package com.abc.demo.Video.Management.Project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.Video.Management.Project.entity.likeEntity;
import com.abc.demo.Video.Management.Project.entity.userEntity;
import com.abc.demo.Video.Management.Project.entity.videoEntity;
import com.abc.demo.Video.Management.Project.repository.likeRepositoryInterface;
import com.abc.demo.Video.Management.Project.repository.userInterface;
import com.abc.demo.Video.Management.Project.repository.videoRepositoryInterface;

@Service
public class likeService {
	@Autowired
	likeRepositoryInterface lri;
	@Autowired
	userInterface ui;
	@Autowired
	videoRepositoryInterface vri;
	@Autowired
	KafkaTemplate<String,String> kt;
	public String addLike(int videoId,int userId)
	{
		videoEntity video=vri.findById(videoId).orElse(null);
		if(video==null)
		{
			return "video Not Present";
		}
		userEntity user=ui.findById(userId).orElse(null);
		if(user==null)
		{
			return "User Not Present";
		}
		likeEntity likes=lri.findByUserUseridAndVideoVideoid(userId, videoId);
		if(likes==null)
		{
			likeEntity like=new likeEntity();
			like.setUser(ui.findById(userId).get());
			like.setVideo(vri.findById(videoId).get());
			lri.save(like);
			userEntity user1=vri.findById(videoId).get().getUser();
			String topic="UserTopic"+user1.getUserid();
			kt.send(topic,ui.findById(userId).get().getUsername()+" is liked your video with videoId: "+vri.findById(videoId).get().getVideoid());
			return "liked Successfully";
		}
		else
		{
			return "already liked";
		}
	}
	public String deleteLike(int videoId,int userId)
	{
		videoEntity video=vri.findById(videoId).orElse(null);
		if(video==null)
		{
			return "video Not Present";
		}
		userEntity user=ui.findById(userId).orElse(null);
		if(user==null)
		{
			return "User Not Present";
		}
		likeEntity likes=lri.findByUserUseridAndVideoVideoid(userId, videoId);
		if(likes!=null)
		{
			likeEntity like=new likeEntity();
			like.setUser(ui.findById(userId).get());
			like.setVideo(vri.findById(videoId).get());
			userEntity user1=vri.findById(videoId).get().getUser();
			String topic="UserTopic"+user1.getUserid();
			kt.send(topic,ui.findById(userId).get().getUsername()+" is unliked to your video with videoId: "+vri.findById(videoId).get().getVideoid());
			lri.delete(like);
			return "deleted Successfully";
		}
		else
		{
			return "Cannot delete like";
		}
	}
}
