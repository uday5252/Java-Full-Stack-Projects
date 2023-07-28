package com.project.videostreamingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.videostreamingproject.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	User findByUserName(String v);
}
