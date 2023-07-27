package com.sam.demo.streaming.app.zee5.REPOSITORY;

import com.sam.demo.streaming.app.zee5.ENTITY.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository  extends JpaRepository<LikeEntity,Integer> {
    int countByVideoId(Long videoId);
    boolean existsByVideoIdAndUserId(Long videoId, Long userId);


}
