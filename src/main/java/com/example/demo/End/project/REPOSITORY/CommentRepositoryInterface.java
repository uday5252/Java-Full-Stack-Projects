package com.example.demo.End.project.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.End.project.ENTITY.CommentEntity;

import jakarta.transaction.Transactional;

public interface CommentRepositoryInterface extends JpaRepository<CommentEntity, Integer>{

//	@Transactional
//
//    @Query(value="SELECT * FROM comment_entity V WHERE V.ue_userid=:uid AND V.ve_videoid=:vid",nativeQuery = true)
	CommentEntity findByUeUseridAndVeVideoid(int id, int vid);

}
