package com.project.demo.end_to_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.end_to_end.entities.User;

public interface UserRepositoryInterface extends JpaRepository<User,Integer>{

    User findByUsername(String username);
    
}
