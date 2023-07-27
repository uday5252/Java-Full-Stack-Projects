package com.abc.demo.Video.Management.Project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.Video.Management.Project.entity.videoEntity;

public interface videoRepositoryInterface extends JpaRepository<videoEntity,Integer>{
	ArrayList<videoEntity> findByUserUseridAndGenreGenreid(int uid,int gid);
	ArrayList<videoEntity> findByUserUserid(int uid);
	ArrayList<videoEntity> findByGenreGenreid(int gid);
}
