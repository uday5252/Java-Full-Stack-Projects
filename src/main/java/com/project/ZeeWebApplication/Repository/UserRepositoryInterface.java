package com.project.ZeeWebApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ZeeWebApplication.Entity.UserEntity;

public interface UserRepositoryInterface extends JpaRepository<UserEntity, Integer >{
	
}
