package com.project.VideoStreamingPlatformUsingSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.project.VideoStreamingPlatformUsingSpringBoot.entity.likesEntity;

import jakarta.transaction.Transactional;

public interface LikeRepository extends JpaRepository<likesEntity, Integer>{
	@Query(value="SELECT * FROM likes l WHERE l.user_id=:userId AND l.video_id=:videoId",nativeQuery = true)
	likesEntity findByuser_video_id(int userId,int videoId);
	@Transactional
	@Modifying
	@Query(value="DELETE FROM likes l WHERE l.user_id=:userId AND l.video_id=:vid",nativeQuery = true)
	void deletebyuser_video_id(int userId, int vid);
}
