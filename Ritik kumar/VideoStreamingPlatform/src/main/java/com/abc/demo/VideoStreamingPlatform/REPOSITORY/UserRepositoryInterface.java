package com.abc.demo.VideoStreamingPlatform.REPOSITORY;

import com.abc.demo.VideoStreamingPlatform.ENTITY.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryInterface extends JpaRepository<UserEntity,Integer> {
}
