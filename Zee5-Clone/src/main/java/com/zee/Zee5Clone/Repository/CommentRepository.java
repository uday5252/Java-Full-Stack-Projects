package com.zee.Zee5Clone.Repository;

import com.zee.Zee5Clone.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {
}
