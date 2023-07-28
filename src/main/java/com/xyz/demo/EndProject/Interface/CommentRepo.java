package com.xyz.demo.EndProject.Interface;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.demo.EndProject.Entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>
{

}
