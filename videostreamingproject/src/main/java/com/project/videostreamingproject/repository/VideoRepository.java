package com.project.videostreamingproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.videostreamingproject.entity.Genre;
import com.project.videostreamingproject.entity.VideoTabel;

public interface VideoRepository extends JpaRepository<VideoTabel,Integer>{

	List<VideoTabel> findAllByGid(Genre id);
    VideoTabel findByTitle(String title);
     
}
