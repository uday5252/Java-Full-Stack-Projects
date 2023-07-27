package com.example.training.Project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.training.Project.Entities.GenreEntity;

public interface GenreRepo extends JpaRepository<GenreEntity,Integer> {
    
}
