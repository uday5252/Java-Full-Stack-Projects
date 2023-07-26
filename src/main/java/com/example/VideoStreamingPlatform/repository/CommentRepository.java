package com.example.VideoStreamingPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.VideoStreamingPlatform.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
