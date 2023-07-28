package com.project.zee.Video.Streaming.Platform.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.zee.Video.Streaming.Platform.Entity.VideoEntity;

public interface VideoInterface extends JpaRepository<VideoEntity ,Integer>{



	List<VideoEntity> findByGenreId(int gid);

}
