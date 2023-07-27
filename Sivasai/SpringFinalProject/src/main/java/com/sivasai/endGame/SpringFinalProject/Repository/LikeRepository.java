package com.sivasai.endGame.SpringFinalProject.Repository;

import com.sivasai.endGame.SpringFinalProject.Entity.Likes;
import com.sivasai.endGame.SpringFinalProject.Entity.User;
import com.sivasai.endGame.SpringFinalProject.Entity.Video;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Integer> {

    List<Likes> findAllByUserId(User user);
    List<Likes> findAllByVideoId(Video video);

//    @Transactional
//    @Modifying
//    @Query("delete from likes where user_id=:userId and video_id=:videoId")
//    void deleteEntry(int userId, int videoId);

    @Transactional
    @Modifying
    void deleteByUserIdAndVideoId(User user, Video video);

    Boolean existsByUserId(User user);
    Boolean existsByVideoId(Video video);

    Boolean existsByUserIdAndVideoId(User user, Video video);
}
