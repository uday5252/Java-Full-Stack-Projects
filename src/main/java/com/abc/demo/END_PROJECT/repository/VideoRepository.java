package com.abc.demo.END_PROJECT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abc.demo.END_PROJECT.entity.VideoEntity;

import jakarta.transaction.Transactional;


public interface VideoRepository extends JpaRepository<VideoEntity,Integer>{

	@Transactional
	@Query(value="SELECT * FROM video V WHERE V.genre_id_id=:gid",nativeQuery = true)
	List<VideoEntity> findByGenreid(int gid);
}
