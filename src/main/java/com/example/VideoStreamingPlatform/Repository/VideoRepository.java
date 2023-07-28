package com.example.VideoStreamingPlatform.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.VideoStreamingPlatform.Entity.GenreEntity;
import com.example.VideoStreamingPlatform.Entity.VideoEntity;

public interface VideoRepository extends JpaRepository<VideoEntity, Integer>{
	public List<VideoEntity> getVideoByGenre(GenreEntity genre);
}
