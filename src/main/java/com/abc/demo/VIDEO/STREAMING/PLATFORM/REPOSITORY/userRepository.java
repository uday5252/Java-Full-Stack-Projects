package com.abc.demo.VIDEO.STREAMING.PLATFORM.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.userEntity;

public interface userRepository extends JpaRepository<userEntity, Integer> {

}
