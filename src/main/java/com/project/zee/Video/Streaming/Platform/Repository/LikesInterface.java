package com.project.zee.Video.Streaming.Platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.zee.Video.Streaming.Platform.Entity.LikesEntity;

public interface LikesInterface extends JpaRepository<LikesEntity,Integer> {

	
	

	

	void deleteByUserIdAndVideoId(int userId, int videoId);

	Object findByVideoIdAndUserId(int videoId, int userId);

	
	

}
