package com.abc.demo.VideoStreamingPlatform.REPOSITORY;

import com.abc.demo.VideoStreamingPlatform.ENTITY.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepositoryInterface extends JpaRepository<GenreEntity,Integer> {
}
