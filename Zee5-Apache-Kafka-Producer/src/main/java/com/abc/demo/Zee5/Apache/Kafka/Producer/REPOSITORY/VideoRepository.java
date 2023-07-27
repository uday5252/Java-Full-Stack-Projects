package com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY;


import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.GenreEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Integer> {
   public List<VideoEntity> findAllByGenre(GenreEntity genre);
}