package com.abc.demo.Video.Management.Project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.Video.Management.Project.entity.commentEntity;

public interface commentRepositoryInterface extends JpaRepository<commentEntity,Integer>{
	ArrayList<commentEntity> findByUserUseridAndVideoVideoid(int userId,int videoId);
}
