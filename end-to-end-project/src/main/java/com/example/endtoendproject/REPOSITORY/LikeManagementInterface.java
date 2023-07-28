package com.example.endtoendproject.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.endtoendproject.ENTITY.Likes;

public interface LikeManagementInterface extends JpaRepository<Likes, Integer> {

}
