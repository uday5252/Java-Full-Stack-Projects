package com.example.endtoendproject.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.endtoendproject.ENTITY.Comments;

public interface CommentManagementInterface extends JpaRepository<Comments, Integer> {

}
