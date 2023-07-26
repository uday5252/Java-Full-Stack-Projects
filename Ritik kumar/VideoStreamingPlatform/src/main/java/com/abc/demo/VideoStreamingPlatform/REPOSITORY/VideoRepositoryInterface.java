package com.abc.demo.VideoStreamingPlatform.REPOSITORY;

import com.abc.demo.VideoStreamingPlatform.ENTITY.GenreEntity;
import com.abc.demo.VideoStreamingPlatform.ENTITY.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepositoryInterface extends JpaRepository<VideoEntity,Integer> {
    public List<VideoEntity> findAllByGenre(GenreEntity ge);
}
