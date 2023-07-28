package com.example.demo.End.project.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.End.project.ENTITY.LikeEntity;
import com.example.demo.End.project.ENTITY.VideoEntity;

import jakarta.transaction.Transactional;

public interface LikeRepositoryInterface extends JpaRepository<LikeEntity, Integer> {

//	@Transactional
//
//    @Query(value="SELECT * FROM like_entity V WHERE V.ue_userid=:uid AND V.ve_videoid=:vid",nativeQuery = true)
//
//    LikeEntity findByUseridAndVideoid(int uid, int vid);

	
//	LikeEntity findByUeUseridAndVeVideoid(int userid, int videoid);

	LikeEntity findByUeUseridAndVeVideoid(int userid, int videoid);

	//LikeEntity findByUeUseridAndVeVideoid(int userid, int videoid);
}
