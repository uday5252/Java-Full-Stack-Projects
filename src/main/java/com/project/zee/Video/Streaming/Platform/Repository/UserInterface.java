package com.project.zee.Video.Streaming.Platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.zee.Video.Streaming.Platform.Entity.UserEntity;

public interface UserInterface extends JpaRepository<UserEntity,Integer>{

}
