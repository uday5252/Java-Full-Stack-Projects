package com.videostreamingapp.com.Assignment.project.REPOSITORY;

import com.videostreamingapp.com.Assignment.project.ENTITY.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
}
