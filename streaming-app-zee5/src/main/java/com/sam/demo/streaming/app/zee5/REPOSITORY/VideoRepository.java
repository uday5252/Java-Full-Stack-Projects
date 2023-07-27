package com.sam.demo.streaming.app.zee5.REPOSITORY;

import com.sam.demo.streaming.app.zee5.ENTITY.GenreEntity;
import com.sam.demo.streaming.app.zee5.ENTITY.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VideoRepository extends JpaRepository<VideoEntity,Integer> {
    List<VideoEntity> findALlByGenre(GenreEntity ge);
}
