package com.project.ZeeWebApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ZeeWebApplication.Entity.LikeEntity;

public interface LikeRepositoryInterface extends JpaRepository<LikeEntity, Integer>{

	LikeEntity findByUserIdAndVideoId(int uid, int vid);
	 

}
