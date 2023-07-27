package com.abc.demo.Zee5.OTT.Project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.Zee5.OTT.Project.Entity.User;
import com.abc.demo.Zee5.OTT.Project.Entity.Video;
import com.abc.demo.Zee5.OTT.Project.Entity.likes;
import com.abc.demo.Zee5.OTT.Project.Repository.LikeRepository;

@Service
public class LikeService {
	@Autowired
	LikeRepository lr;
	@Autowired
	KafkaAdmin Admin;
	@Autowired
	KafkaTemplate<String, String> kt;
	public void addlike(User user,Video video)
	{
		likes like=new likes();
		if(lr.findByUserAndVideo(user, video)==null)
		{
		like.setUser(user);
		like.setVideo(video);
		lr.save(like);
		String User="user"+like.getUser().getUser_id();
		
		kt.send(User,"USER "+like.getUser().getUser_id()+" has liked the video "+like.getVideo().getVideo_id());
		}
	}
	public void deletelike(User user,Video video)
	{
		likes like=lr.findByUserAndVideo(user,video);
		if(lr.findByUserAndVideo(user, video)!=null)
		{
			lr.deleteById(like.getLike_id());

		}
	}
	
}
