package com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
}
