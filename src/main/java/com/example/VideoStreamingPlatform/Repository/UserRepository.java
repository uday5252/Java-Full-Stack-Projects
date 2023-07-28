package com.example.VideoStreamingPlatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.VideoStreamingPlatform.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
