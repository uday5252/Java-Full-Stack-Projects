package com.example.VideoStreamingPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.VideoStreamingPlatform.entity.Likes;

public interface LikeRepository extends JpaRepository<Likes, Integer> {

}
