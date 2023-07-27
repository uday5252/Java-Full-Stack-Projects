package com.abc.Streaming.application.project.REPOSITORIES;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.Streaming.application.project.ENTITIES.GenreEntity;

public interface GenreEntityInterfaceRepositary extends JpaRepository<GenreEntity, Integer>
{

}
