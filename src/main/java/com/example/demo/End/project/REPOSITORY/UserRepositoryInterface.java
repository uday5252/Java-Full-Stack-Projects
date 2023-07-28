package com.example.demo.End.project.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.End.project.ENTITY.UserEntity;

public interface UserRepositoryInterface extends JpaRepository<UserEntity, Integer> {

}
