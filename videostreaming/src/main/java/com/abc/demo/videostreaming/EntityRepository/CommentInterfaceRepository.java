package com.abc.demo.videostreaming.EntityRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.videostreaming.Entity.CommentEntity;
import com.abc.demo.videostreaming.Entity.LikeId;

public interface CommentInterfaceRepository extends JpaRepository<CommentEntity,LikeId>{
    
}
