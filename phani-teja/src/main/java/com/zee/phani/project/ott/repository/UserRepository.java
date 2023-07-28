package com.zee.phani.project.ott.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zee.phani.project.ott.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
