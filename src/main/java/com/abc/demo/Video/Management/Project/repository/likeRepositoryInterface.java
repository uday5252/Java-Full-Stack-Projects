package com.abc.demo.Video.Management.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.Video.Management.Project.entity.likeEntity;

public interface likeRepositoryInterface extends JpaRepository<likeEntity,Integer>{
	likeEntity findByUserUseridAndVideoVideoid(int userId,int videoId);
}
