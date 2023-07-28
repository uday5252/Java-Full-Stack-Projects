package com.example.endtoendproject.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.endtoendproject.ENTITY.User;

public interface UserManagementInterface extends JpaRepository<User, Integer>{

}
