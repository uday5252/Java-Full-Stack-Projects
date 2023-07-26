package com.example.VideoStreamingPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.VideoStreamingPlatform.entity.Genre;
import com.example.VideoStreamingPlatform.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Integer> {
  public List<Video> findAllByGenre(Genre genre);
}
