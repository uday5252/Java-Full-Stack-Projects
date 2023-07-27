package com.videostreamingapp.com.Assignment.project.REPOSITORY;

import com.videostreamingapp.com.Assignment.project.ENTITY.CommentEntity;
import com.videostreamingapp.com.Assignment.project.ENTITY.UserEntity;
import com.videostreamingapp.com.Assignment.project.ENTITY.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {

    CommentEntity findByVideoAndUser(VideoEntity video, UserEntity user);
}
