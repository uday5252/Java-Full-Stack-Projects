package com.abc.demo.Final.Project.implementing.all.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.Final.Project.implementing.all.Entity.CommentEntity;
import com.abc.demo.Final.Project.implementing.all.Entity.UserEntity;
import com.abc.demo.Final.Project.implementing.all.Entity.VideoEntity;

public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {

	public String findByUsercomAndVideocom(UserEntity usercom,VideoEntity videocom);
}
