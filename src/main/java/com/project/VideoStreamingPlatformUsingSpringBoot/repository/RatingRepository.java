package com.project.VideoStreamingPlatformUsingSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.VideoStreamingPlatformUsingSpringBoot.entity.ratingEntity;

public interface RatingRepository extends JpaRepository<ratingEntity, Integer>{
	@Query(value="SELECT * FROM ratings r WHERE r.user_id=:userId AND r.video_id=:vid",nativeQuery = true)
	ratingEntity findbyuser_video_id(int userId, int vid);

}
