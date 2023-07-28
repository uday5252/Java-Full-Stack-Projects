package com.project.demo.end_to_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.end_to_end.entities.Video;

public interface VideoRepositoryInterface extends JpaRepository<Video,Integer>{
    public List<Video> findByGenreId(int id);
}
