package com.example.training.Project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.training.Project.Entities.CommentEntity;

public interface CommentRepo extends JpaRepository<CommentEntity,Integer> {
    CommentEntity findByVideoIdIdAndUserIdId(int vid,int uid);
}
