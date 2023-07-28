package com.xyz.demo.EndProject.Interface;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.demo.EndProject.Entity.GenreEntity;
import com.xyz.demo.EndProject.Entity.VideoEntity;

public interface VideoRepo extends JpaRepository<VideoEntity,Integer>
{

	List<VideoEntity> findByGenreId(GenreEntity genre);

}
