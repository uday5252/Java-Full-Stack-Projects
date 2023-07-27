package com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
        }
