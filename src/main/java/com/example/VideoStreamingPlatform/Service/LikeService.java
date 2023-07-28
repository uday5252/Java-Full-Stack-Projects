package com.example.VideoStreamingPlatform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.VideoStreamingPlatform.Entity.LikeEntity;
import com.example.VideoStreamingPlatform.Entity.UserEntity;
import com.example.VideoStreamingPlatform.Entity.VideoEntity;
import com.example.VideoStreamingPlatform.Repository.LikeRepository;
import com.example.VideoStreamingPlatform.Repository.UserRepository;
import com.example.VideoStreamingPlatform.Repository.VideoRepository;

@Service
public class LikeService {
	
	@Autowired
	LikeRepository lr;
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	VideoRepository vr;
	
	@Autowired
	KafkaAdmin admin;
	
	@Autowired
	KafkaTemplate<String, String> kt;
	
	public void addLike(LikeEntity like, int video_id, int user_id) {
		like.setUser(ur.findById(user_id).get());
		like.setVideo(vr.findById(video_id).get());
		lr.save(like);
		String Like = "create_like_topic";
		admin.createOrModifyTopics(TopicBuilder.name(Like).build());
		kt.send(Like, "Video with id:"+video_id+" was liked by "+ur.findById(user_id).get().getUsername()); 
	}
	
	public void removeLike(int video_id, int user_id) {
//		VideoEntity video=vr.findById(video_id).get();
//		UserEntity user = ur.findById(user_id).get();
		LikeEntity like = lr.findByVideoIdAndUserId(video_id, user_id);
		lr.deleteById(like.getLike_id());
	}
}
