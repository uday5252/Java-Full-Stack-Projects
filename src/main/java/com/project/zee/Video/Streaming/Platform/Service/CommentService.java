package com.project.zee.Video.Streaming.Platform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.zee.Video.Streaming.Platform.Entity.CommentEntity;
import com.project.zee.Video.Streaming.Platform.Entity.UserEntity;
import com.project.zee.Video.Streaming.Platform.Entity.VideoEntity;
import com.project.zee.Video.Streaming.Platform.Repository.CommentInterface;
import com.project.zee.Video.Streaming.Platform.Repository.UserInterface;
import com.project.zee.Video.Streaming.Platform.Repository.VideoInterface;

import jakarta.transaction.Transactional;

@Service
public class CommentService {
	
	@Autowired
	CommentInterface ci;
	
	@Autowired
	VideoInterface vi;
	
	@Autowired 
	UserInterface ui;
	
	@Autowired
	KafkaTemplate<Integer,String> kt;
	
	public void CommentAVideo(CommentEntity comment ,int videoId,int userId)
	{
		VideoEntity video = vi.findById(videoId).get();
		UserEntity user = ui.findById(userId).get();
		comment.setUser(user);
		comment.setVideo(video);
		ci.save(comment);
	}
	
	public void UpdateComment(CommentEntity updatedcomment,int videoId,int userId)
	{
		CommentEntity comment = ci.findByUserIdAndVideoId(userId,videoId);
		comment.setDescription(updatedcomment.getDescription() != null ? updatedcomment.getDescription() : comment.getDescription());
		ci.save(comment);
	}
	
	@Transactional
	public void deleteComment(int userId,int videoId)
	{
		ci.deleteByUserIdAndVideoId(userId,videoId);
	}
	
	//METHODS TO SEND DATA TO TOPICS
	
	public void sendCommentsUpdatetoCommentsTopic(int userid,int videoid)
	{
		String CommentsUpdate = "User " + userid + " commented on Video "+ videoid;
		kt.send("CommentsDataTopic",CommentsUpdate);
	
		
	}

	public void sendDeletedCommentsUpdateToCommentsTopic(int userid, int videoid)
	{
		String CommentsUpdate = "User " + userid + " uncommented on Video "+ videoid;
		kt.send("CommentsDataTopic",CommentsUpdate);
	
	}
	
	public void sendEditedCommentsUpdateToCommentsTopic(int userid, int videoid)
	{
		String CommentsUpdate = "User " + userid + " updated the comment on Video "+ videoid;
		kt.send("CommentsDataTopic",CommentsUpdate);
	
	}
	

}
