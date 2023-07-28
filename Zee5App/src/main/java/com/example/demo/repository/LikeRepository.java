package com.example.demo.repository;

import com.example.demo.entity.LikeId;
import com.example.demo.entity.LikeEntity;
import com.example.demo.entity.User;
import com.example.demo.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity,LikeId> {


}
