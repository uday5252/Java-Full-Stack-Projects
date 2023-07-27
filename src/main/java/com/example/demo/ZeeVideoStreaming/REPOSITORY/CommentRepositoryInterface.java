package com.example.demo.ZeeVideoStreaming.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ZeeVideoStreaming.ENTITY.CommentEntity;

public interface CommentRepositoryInterface extends JpaRepository<CommentEntity, Long> {
	List<CommentEntity> findByVideoId(long vid);
}
