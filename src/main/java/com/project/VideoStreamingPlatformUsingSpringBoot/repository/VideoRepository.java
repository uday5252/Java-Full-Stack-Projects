package com.project.VideoStreamingPlatformUsingSpringBoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.VideoStreamingPlatformUsingSpringBoot.entity.videosEntity;

public interface VideoRepository extends JpaRepository<videosEntity, Integer>{
	@Query(value="SELECT * FROM videos v WHERE v.genre_Id=:genreId",nativeQuery = true)
	List<videosEntity> findBygenreid(int genreId);
}
