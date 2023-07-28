package com.project.videostreamingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.videostreamingproject.entity.Comments;
import com.project.videostreamingproject.entity.User;
import com.project.videostreamingproject.entity.VideoTabel;

public interface CommentRepo extends JpaRepository<Comments,Integer> {
Comments findByUAndV(User u,VideoTabel v);
}
