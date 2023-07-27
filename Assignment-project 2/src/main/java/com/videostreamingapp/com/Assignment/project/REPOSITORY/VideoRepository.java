package com.videostreamingapp.com.Assignment.project.REPOSITORY;

import com.videostreamingapp.com.Assignment.project.ENTITY.GenreEntity;
import com.videostreamingapp.com.Assignment.project.ENTITY.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity,Integer> {
    List<VideoEntity> findAllByGenre(GenreEntity genre);
}
