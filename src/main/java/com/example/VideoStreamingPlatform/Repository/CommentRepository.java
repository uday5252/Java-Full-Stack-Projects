package com.example.VideoStreamingPlatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.VideoStreamingPlatform.Entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
	public CommentEntity findByVideoIdAndUserId(int videoId, int userId);
}
