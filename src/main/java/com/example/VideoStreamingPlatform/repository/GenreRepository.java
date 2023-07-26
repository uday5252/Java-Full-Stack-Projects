package com.example.VideoStreamingPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.VideoStreamingPlatform.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
