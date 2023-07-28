package com.abc.demo.VIDEO.STREAMING.PLATFORM.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.likeEntity;

public interface likeRepository extends JpaRepository<likeEntity, Integer>{

	likeEntity findByVideoIdAndUserId(int videoId, int userId);

	List<likeEntity> findByVideoId(int videoId);
	

}
