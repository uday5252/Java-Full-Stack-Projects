package com.sivasai.endGame.SpringFinalProject.Service;

import com.sivasai.endGame.SpringFinalProject.Entity.Likes;
import com.sivasai.endGame.SpringFinalProject.Entity.User;
import com.sivasai.endGame.SpringFinalProject.Entity.Video;
import com.sivasai.endGame.SpringFinalProject.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    LikeRepository likeRepository;

    public Likes saveLike(Likes like){
        return likeRepository.save(like);
    }

    public List<Likes> getLikesByUser(User user){
        return likeRepository.findAllByUserId(user);
    }

    public List<Likes> getLikesByVideo(Video video){
        return likeRepository.findAllByVideoId(video);
    }

    public void deleteUserLike(User user, Video video){
        likeRepository.deleteByUserIdAndVideoId(user, video);
    }

    public Boolean checkIfExistsByUser(User user){
        return likeRepository.existsByUserId(user);
    }

    public Boolean checkIfExistsByVideo(Video video){
        return likeRepository.existsByVideoId(video);
    }

    public Boolean checkIfExistsByUserAndVideo(User user, Video video){
        return likeRepository.existsByUserIdAndVideoId(user, video);
    }
}
