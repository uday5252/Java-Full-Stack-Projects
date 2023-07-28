package com.example.VideoStreamingPlatform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.VideoStreamingPlatform.Entity.CommentEntity;
import com.example.VideoStreamingPlatform.Entity.LikeEntity;
import com.example.VideoStreamingPlatform.Repository.CommentRepository;
import com.example.VideoStreamingPlatform.Repository.UserRepository;
import com.example.VideoStreamingPlatform.Repository.VideoRepository;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository cr;
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	VideoRepository vr;
	
	@Autowired
	KafkaAdmin admin;
	
	@Autowired
	KafkaTemplate<String, String> kt;
	
	//Add Comment
	public void addComment(CommentEntity comment, int video_id, int user_id) {
		comment.setUser(ur.findById(user_id).get());
		comment.setVideo(vr.findById(video_id).get());
		cr.save(comment);
		String Comment = "create_comment_topic";
		admin.createOrModifyTopics(TopicBuilder.name(Comment).build());
		kt.send(Comment, "A new comment was added on a video with id:"+video_id+" by "+ur.findById(user_id).get().getUsername()); 
	}
	
	public void removeComment(int video_id, int user_id) {

		CommentEntity comment = cr.findByVideoIdAndUserId(video_id, user_id);
		cr.deleteById(comment.getComment_id());
	}
}
