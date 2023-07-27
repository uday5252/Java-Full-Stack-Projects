package com.abc.Streaming.application.project.REPOSITORIES;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.Streaming.application.project.ENTITIES.VideoEntity;

public interface VideoEntityInterfaceRepositary extends JpaRepository<VideoEntity,Integer >
{
	List<VideoEntity> findByGenreIdId(int gid);
	
}
