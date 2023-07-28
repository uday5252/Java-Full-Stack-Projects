package com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.likeEntity;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.REPOSITORY.likeRepository;


@Service
public class likeService {

	@Autowired
	likeRepository lr;

	public void addlike(likeEntity like) {
	
		
		lr.save(like);
		
		
	}

	public boolean hasUserLikedVideo(int videoId, int userId) {
		return lr.findByVideoIdAndUserId(videoId, userId) != null;
	}
	
	


	public void unlikeVideo(int videoId, int userId) {
		likeEntity like=lr.findByVideoIdAndUserId(videoId, userId);
		
		lr.delete(like);
		
	}
	
	
	public List<likeEntity> getLikesByVideoId(int videoId) {
		
		List<likeEntity>likes=lr.findByVideoId(videoId);
		
		return likes;
	}
}
