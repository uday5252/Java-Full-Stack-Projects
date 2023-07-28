package com.project.ZeeWebApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ZeeWebApplication.Entity.VideoEntity;

public interface VideoRepositoryInterface extends JpaRepository<VideoEntity, Integer>{

	List<VideoEntity> findByGenreId(int gid);

}
