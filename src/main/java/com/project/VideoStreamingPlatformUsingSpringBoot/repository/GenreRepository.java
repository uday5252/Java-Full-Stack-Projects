package com.project.VideoStreamingPlatformUsingSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.VideoStreamingPlatformUsingSpringBoot.entity.genresEntity;

public interface GenreRepository extends JpaRepository<genresEntity, Integer>{

}
