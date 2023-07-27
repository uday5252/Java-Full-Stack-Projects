package com.example.demo.ZeeVideoStreaming.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ZeeVideoStreaming.ENTITY.UserEntity;

public interface UserRepositoryInterface extends JpaRepository<UserEntity, Long> {

}
