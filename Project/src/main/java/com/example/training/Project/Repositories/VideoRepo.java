package com.example.training.Project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.training.Project.Entities.VideoEntity;

public interface VideoRepo extends JpaRepository<VideoEntity,Integer> {
    
}
