package com.project.zee.Video.Streaming.Platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.zee.Video.Streaming.Platform.Entity.CommentEntity;

public interface CommentInterface extends JpaRepository<CommentEntity,Integer>{

	void deleteByUserIdAndVideoId(int userId, int videoId);

	CommentEntity findByUserIdAndVideoId(int userId, int videoId);

}
