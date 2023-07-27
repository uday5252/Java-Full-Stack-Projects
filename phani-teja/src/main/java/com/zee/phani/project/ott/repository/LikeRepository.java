package com.zee.phani.project.ott.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zee.phani.project.ott.entity.IdClassification;
import com.zee.phani.project.ott.entity.LikeEntity;

public interface LikeRepository extends JpaRepository<LikeEntity, IdClassification> {

    Optional<LikeEntity> findByUserUserIdAndVideoId(int userId, int Id);
}
