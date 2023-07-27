package com.abc.demo.Final.Project.implementing.all.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.Final.Project.implementing.all.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

}
