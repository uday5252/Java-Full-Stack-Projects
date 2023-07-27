package com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
