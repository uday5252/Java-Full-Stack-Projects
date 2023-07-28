package com.abc.demo.END_PROJECT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.END_PROJECT.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity,Integer>{

}
