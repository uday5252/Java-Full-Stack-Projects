package com.project.demo.end_to_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.end_to_end.entities.Likes;

public interface LikeRepositoryInterface extends JpaRepository<Likes,Integer>{
    Likes findByLikedbyUsernameAndVideoId(String username, int videoId);

    List<Likes> findByVideoId(int videoId);
}
