package com.example.VideoStreamingPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.VideoStreamingPlatform.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
