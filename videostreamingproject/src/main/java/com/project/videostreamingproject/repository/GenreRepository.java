package com.project.videostreamingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.videostreamingproject.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre,Integer>{
      Genre findByGenreName(String v);
}
