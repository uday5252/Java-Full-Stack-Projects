package com.project.demo.end_to_end.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.end_to_end.entities.Likes;
import com.project.demo.end_to_end.repository.LikeRepositoryInterface;

@Service
public class LikeService {
    @Autowired
    LikeRepositoryInterface lri;
    public void addLike(Likes like){
        lri.save(like);
    }
    public Likes findByUserAndVideo(String username, int videoId) {
        return lri.findByLikedbyUsernameAndVideoId(username, videoId);
    }
    public List<Likes> findByVideo(int videoId){
        return lri.findByVideoId(videoId);
    }
    public void delete(Likes findByUserAndVideo) {
        lri.delete(findByUserAndVideo);
    }
}
