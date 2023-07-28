package com.xyz.demo.EndProject.Interface;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.demo.EndProject.Entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer>{

}
