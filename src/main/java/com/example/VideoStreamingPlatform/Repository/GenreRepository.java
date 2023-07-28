package com.example.VideoStreamingPlatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.VideoStreamingPlatform.Entity.GenreEntity;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer>{

}
