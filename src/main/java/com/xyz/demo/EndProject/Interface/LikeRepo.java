package com.xyz.demo.EndProject.Interface;


import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.demo.EndProject.Entity.Like;

public interface LikeRepo extends JpaRepository<Like, Integer>
{

	Like findByUserUserIdAndVideoVideoId(int userId, int videoId);
	
}
