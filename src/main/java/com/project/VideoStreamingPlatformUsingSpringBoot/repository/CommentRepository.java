package com.project.VideoStreamingPlatformUsingSpringBoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.VideoStreamingPlatformUsingSpringBoot.entity.commentsEntity;

public interface CommentRepository extends JpaRepository<commentsEntity, Integer>{
	@Query(value="SELECT * FROM comments c WHERE c.video_Id=:vid",nativeQuery = true)
	List<commentsEntity> findCommentsByvideoid(int vid);
}
