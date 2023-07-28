package com.example.endtoendproject.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.endtoendproject.ENTITY.Genre;

public interface GenreMangementInterface extends JpaRepository<Genre, Integer>{

}
