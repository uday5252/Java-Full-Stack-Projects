package com.abc.demo.END_PROJECT.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abc.demo.END_PROJECT.entity.LikeEntity;

import jakarta.transaction.Transactional;

public interface LikeRepository extends JpaRepository<LikeEntity,Integer>{

	@Transactional
	@Query(value="SELECT * FROM likes l WHERE l.user_id=:uid AND l.video_id=:vid",nativeQuery = true)
	LikeEntity findByUser_Video_Id(int uid,int vid);
}
