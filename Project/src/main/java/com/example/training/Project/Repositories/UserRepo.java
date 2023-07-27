package com.example.training.Project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.training.Project.Entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity,Integer>{
    
}
