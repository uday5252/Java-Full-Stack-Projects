package com.example.demo.ZeeVideoStreaming.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ZeeVideoStreaming.ENTITY.LikeEntity;

public interface LikeRepositoryInterface extends JpaRepository<LikeEntity, Long> {
	LikeEntity findByUserIdAndVideoId(long uid, long vid);

	List<LikeEntity> findByVideoId(long vid);
}
