package com.example.training.Project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.training.Project.Entities.LikeEntity;

public interface LikeRepo extends JpaRepository<LikeEntity,Integer>{
    LikeEntity findByUserIdIdAndVideoIdId(int uid,int vid);
}
