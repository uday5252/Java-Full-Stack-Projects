package com.example.VideoStreamingPlatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.VideoStreamingPlatform.Entity.LikeEntity;
import com.example.VideoStreamingPlatform.Entity.UserEntity;
import com.example.VideoStreamingPlatform.Entity.VideoEntity;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer>{
	public LikeEntity findByVideoIdAndUserId(int videoId, int userId);
}
