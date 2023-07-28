package com.abc.demo.videostreaming.EntityRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.videostreaming.Entity.Genre;


public interface GenreInterfaceRepository extends JpaRepository<Genre,Integer> {
    Genre findByName(String name);
}
