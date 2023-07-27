package com.sam.demo.streaming.app.zee5.REPOSITORY;

import com.sam.demo.streaming.app.zee5.ENTITY.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
}
