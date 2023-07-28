package com.abc.demo.videostreaming.EntityRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.videostreaming.Entity.LikeEntity;
import com.abc.demo.videostreaming.Entity.LikeId;

public interface LikeInterfaceRepository extends JpaRepository<LikeEntity,LikeId> {
    
}
