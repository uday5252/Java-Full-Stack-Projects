package com.example.demo.End.project.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.End.project.ENTITY.GenreEntity;

public interface GenreRepositoryInterface extends JpaRepository<GenreEntity,Integer > {

}
