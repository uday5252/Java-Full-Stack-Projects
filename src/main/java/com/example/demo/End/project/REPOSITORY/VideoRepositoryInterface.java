package com.example.demo.End.project.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.End.project.ENTITY.GenreEntity;
import com.example.demo.End.project.ENTITY.VideoEntity;

import jakarta.transaction.Transactional;

public interface VideoRepositoryInterface extends JpaRepository<VideoEntity, Integer>{
//	@Transactional
//
//    @Query(value="SELECT * FROM video_entity V WHERE V.genre_genreid=gid",nativeQuery = true)

    List<VideoEntity> findByGenreGenreid(int gid);
}
