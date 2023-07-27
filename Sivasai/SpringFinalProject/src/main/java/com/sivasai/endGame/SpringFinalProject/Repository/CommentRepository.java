package com.sivasai.endGame.SpringFinalProject.Repository;

import com.sivasai.endGame.SpringFinalProject.Entity.Comments;
import com.sivasai.endGame.SpringFinalProject.Entity.User;
import com.sivasai.endGame.SpringFinalProject.Entity.Video;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {
    List<Comments> findByVideoId(Video video);
    Boolean existsByVideoId(Video video);

    Optional<Comments> findByUserIdAndVideoId(User user, Video video);

    @Transactional
    @Modifying
    void deleteByUserIdAndVideoId(User user, Video video);

    Boolean existsByUserIdAndVideoId(User user, Video video);
}
