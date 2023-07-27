package com.abc.Streaming.application.project.REPOSITORIES;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.Streaming.application.project.ENTITIES.CommentEntity;

public interface CommentInterfaceRepositary extends JpaRepository<CommentEntity, Integer> {

	CommentEntity findByVideoId(int id);
}
