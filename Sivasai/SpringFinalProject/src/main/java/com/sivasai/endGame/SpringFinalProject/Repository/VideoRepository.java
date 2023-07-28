package com.sivasai.endGame.SpringFinalProject.Repository;

import com.sivasai.endGame.SpringFinalProject.Entity.User;
import com.sivasai.endGame.SpringFinalProject.Entity.Video;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Integer> {
    Optional<Video> findByTitle(String title);
    Boolean existsByTitle(String title);

    @Transactional
    @Modifying
    void deleteByUploadedBy(User user);

    Boolean existsByUploadedBy(User user);

}