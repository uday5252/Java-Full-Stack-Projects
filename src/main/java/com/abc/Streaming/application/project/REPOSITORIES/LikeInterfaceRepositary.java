package com.abc.Streaming.application.project.REPOSITORIES;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abc.Streaming.application.project.ENTITIES.LikeEntity;
import com.abc.Streaming.application.project.ENTITIES.VideoEntity;

public interface LikeInterfaceRepositary extends JpaRepository<LikeEntity, Integer> {

	LikeEntity findByUserId(int id);
}
