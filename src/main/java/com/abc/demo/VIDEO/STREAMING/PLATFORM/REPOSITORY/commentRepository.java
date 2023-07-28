package com.abc.demo.VIDEO.STREAMING.PLATFORM.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.commentEntity;

public interface commentRepository extends JpaRepository<commentEntity,Integer>{

	commentEntity findByVideoIdAndUserId(int vid, int uid);

	List<commentEntity> findByVideoId(int id);
	

}