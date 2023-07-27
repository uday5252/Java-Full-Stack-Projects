package com.abc.demo.Zee5.OTT.Project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.Zee5.OTT.Project.Entity.Genre;
import com.abc.demo.Zee5.OTT.Project.Entity.Video;

public interface VideoRepository extends JpaRepository<Video, Integer>{

	List<Video> findByGenre(Genre genre);
	
}
