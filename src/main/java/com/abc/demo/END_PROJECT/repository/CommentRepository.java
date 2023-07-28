package com.abc.demo.END_PROJECT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abc.demo.END_PROJECT.entity.CommentsEntity;

import jakarta.transaction.Transactional;


public interface CommentRepository extends JpaRepository<CommentsEntity,Integer> {

	@Transactional
	@Query(value="SELECT * FROM comments c WHERE c.user_id=:uid AND c.video_id=:vid",nativeQuery = true)
	CommentsEntity findByUser_Video_Id(int uid,int vid);
}
