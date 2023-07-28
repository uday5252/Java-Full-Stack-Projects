package com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.commentEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.likeEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.REPOSITORY.commentRepository;

@Service
public class commentService {
	
	@Autowired
	commentRepository cr;

	public void addCommentToVideo(commentEntity comment) {
		
		cr.save(comment);
		
	}

	public void deleteCommentToVideo(int videoId, int userId) {
	
		commentEntity ce=cr.findByVideoIdAndUserId(videoId, userId);
		
		cr.delete(ce);
		
	}

	public boolean hasUserCommentedVideo(int videoId, int userId) {
		
		return cr.findByVideoIdAndUserId(videoId, userId)!=null;
		
	}

	public void editComment(commentEntity ce, int i) {
		
		commentEntity comment=cr.findById(i).get();
		
		
		comment.setComment(ce.getComment() != null ? ce.getComment():comment.getComment());

		cr.save(comment);
		
	}

	public List<commentEntity> getCommentsByVideoId(int id) {
		
		List<commentEntity>comments=cr.findByVideoId(id);
		
		return comments;
	}

	
	
	

}
