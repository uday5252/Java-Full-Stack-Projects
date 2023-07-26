package com.abc.demo.VideoStreamingPlatform.REPOSITORY;

import com.abc.demo.VideoStreamingPlatform.ENTITY.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositoryInterface extends JpaRepository<CommentEntity,Integer> {
}
