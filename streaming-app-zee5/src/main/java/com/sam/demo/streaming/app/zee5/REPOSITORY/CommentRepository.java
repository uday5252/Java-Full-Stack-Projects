package com.sam.demo.streaming.app.zee5.REPOSITORY;


import com.sam.demo.streaming.app.zee5.ENTITY.CommentEntity;
import com.sam.demo.streaming.app.zee5.ENTITY.UserEntity;
import com.sam.demo.streaming.app.zee5.ENTITY.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
        CommentEntity findByVideoIdAndUserId(int videoId, int userId);

    }


