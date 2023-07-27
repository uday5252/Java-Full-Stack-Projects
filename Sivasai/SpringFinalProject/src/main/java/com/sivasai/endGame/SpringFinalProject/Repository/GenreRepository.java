package com.sivasai.endGame.SpringFinalProject.Repository;

import com.sivasai.endGame.SpringFinalProject.Entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findByName(String name);
    Boolean existsByName(String name);
}
