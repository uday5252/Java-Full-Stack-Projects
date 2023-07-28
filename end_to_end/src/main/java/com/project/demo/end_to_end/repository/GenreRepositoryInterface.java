package com.project.demo.end_to_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.end_to_end.entities.Genre;

public interface GenreRepositoryInterface extends JpaRepository<Genre,Integer>{

    Genre findByName(String genre);
    
}
