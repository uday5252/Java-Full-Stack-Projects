package com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
}
