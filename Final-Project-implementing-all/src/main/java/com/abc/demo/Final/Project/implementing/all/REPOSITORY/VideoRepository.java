package com.abc.demo.Final.Project.implementing.all.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.Final.Project.implementing.all.Entity.GenreEntity;
import com.abc.demo.Final.Project.implementing.all.Entity.VideoEntity;

public interface VideoRepository extends JpaRepository<VideoEntity,Integer>{

	public List<VideoEntity> findAllByGenre(GenreEntity genre);
}
