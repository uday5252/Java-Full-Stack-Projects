package com.abc.demo.Video.Management.Project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.Video.Management.Project.entity.commentEntity;
import com.abc.demo.Video.Management.Project.entity.userEntity;
import com.abc.demo.Video.Management.Project.entity.videoEntity;
import com.abc.demo.Video.Management.Project.repository.commentRepositoryInterface;
import com.abc.demo.Video.Management.Project.repository.userInterface;
import com.abc.demo.Video.Management.Project.repository.videoRepositoryInterface;

@Service
public class commentService {
	@Autowired
	commentRepositoryInterface cri;
	@Autowired
	userInterface ui;
	@Autowired
	videoRepositoryInterface vri;
	@Autowired
	KafkaTemplate<String,String> kt;
	public String addComment(int userId,int videoId)
	{
		userEntity ue=ui.findById(userId).orElse(null);
		if(ue==null) return "User doesnot exist";
		videoEntity ve=vri.findById(videoId).orElse(null);
		if(ve==null) return "Video doesnot exist";
		commentEntity ce=new commentEntity();
		ce.setUser(ue);
		ce.setVideo(ve);
		cri.save(ce);
		userEntity user1=vri.findById(videoId).get().getUser();
		String topic="UserTopic"+user1.getUserid();
		kt.send(topic,ui.findById(userId).get().getUsername()+" is commented on your video with videoId: "+vri.findById(videoId).get().getVideoid());
		return "Comment added";
	}
	public String deleteComment(int commentId)
	{
		commentEntity ce=cri.findById(commentId).get();
		int userId=ce.getUser().getUserid();
		int videoId=ce.getVideo().getVideoid();
		userEntity user1=vri.findById(videoId).get().getUser();
		String topic="UserTopic"+user1.getUserid();
		kt.send(topic,ui.findById(userId).get().getUsername()+" is deleted his comment on your video with videoId: "+vri.findById(videoId).get().getVideoid());
		cri.delete(ce);
		return "Comment is deleted";
	}
}
