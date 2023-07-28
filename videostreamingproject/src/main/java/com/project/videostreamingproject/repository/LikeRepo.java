package com.project.videostreamingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.videostreamingproject.entity.LikeTabel;
import com.project.videostreamingproject.entity.User;
import com.project.videostreamingproject.entity.VideoTabel;

public interface LikeRepo extends JpaRepository<LikeTabel,Integer> {
    
	
	
			LikeTabel findByUAndV( User u , VideoTabel v);
}
