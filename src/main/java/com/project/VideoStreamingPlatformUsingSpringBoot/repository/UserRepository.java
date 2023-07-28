package com.project.VideoStreamingPlatformUsingSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.VideoStreamingPlatformUsingSpringBoot.entity.usersEntity;

public interface UserRepository extends JpaRepository<usersEntity, Integer> {

}
