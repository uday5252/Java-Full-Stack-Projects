package com.example.demo.ZeeVideoStreaming.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ZeeVideoStreaming.ENTITY.VideoEntity;

public interface VideoRepositoryInterface extends JpaRepository<VideoEntity, Long>{
	List<VideoEntity> findByGenreId(long gid);
}
