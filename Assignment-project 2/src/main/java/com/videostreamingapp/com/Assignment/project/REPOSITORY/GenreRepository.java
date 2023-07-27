package com.videostreamingapp.com.Assignment.project.REPOSITORY;

import com.videostreamingapp.com.Assignment.project.ENTITY.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity,Integer> {
}
