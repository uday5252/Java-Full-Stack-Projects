package com.abc.demo.VIDEO.STREAMING.PLATFORM.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.VideoEntity;

public interface videoRepository extends JpaRepository<VideoEntity, Integer> {

	List<VideoEntity> findAllByGenreId(int genreId);

}
