package com.abc.Streaming.application.project.REPOSITORIES;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.Streaming.application.project.ENTITIES.UserEntity;

public interface UserEntityInterfaceRepositary extends JpaRepository<UserEntity, Integer>
{

}
