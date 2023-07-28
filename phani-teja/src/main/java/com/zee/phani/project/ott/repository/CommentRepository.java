package com.zee.phani.project.ott.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zee.phani.project.ott.entity.CommentEntity;
import com.zee.phani.project.ott.entity.IdClassification;

public interface CommentRepository extends JpaRepository<CommentEntity, IdClassification> {

    Optional<CommentEntity> findByUserUserIdAndVideoId(int userId, int Id);
}
