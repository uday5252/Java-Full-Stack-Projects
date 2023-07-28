package com.project.ZeeWebApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ZeeWebApplication.Entity.CommentEntity;

public interface CommentRepositoryInterface extends JpaRepository<CommentEntity, Integer>{

	void deleteByUserIdAndVideoId(int uid, int vid);



}
