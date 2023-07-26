package com.abc.demo.VideoStreamingPlatform.REPOSITORY;

import com.abc.demo.VideoStreamingPlatform.ENTITY.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepositoryInterface extends JpaRepository<LikeEntity,Integer> {
}
