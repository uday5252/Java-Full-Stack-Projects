package com.example.endtoendproject.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.endtoendproject.ENTITY.Genre;
import com.example.endtoendproject.ENTITY.Video;

public interface VideoManagementInterface extends JpaRepository<Video, Integer> {
	public List<Video> findBygenre(Genre genre);
}
