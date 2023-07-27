package com.zee.phani.project.ott.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zee.phani.project.ott.entity.VideoEntity;

public interface VideoRepository extends JpaRepository<VideoEntity, Integer> {
    List<VideoEntity> findByGenreId(int genreId);
}
