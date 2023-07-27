package com.example.training.Project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.training.Project.Entities.LikeEntity;
import com.example.training.Project.Repositories.LikeRepo;
import com.example.training.Project.Repositories.UserRepo;
import com.example.training.Project.Repositories.VideoRepo;

@Service
public class LikeService {
    
    @Autowired
    LikeRepo lRepo;

    @Autowired
    UserRepo uRepo;

    @Autowired
    VideoRepo vRepo;

    public void setLike(LikeEntity like,int uid,int vid){
        like.setUserId(uRepo.findById(uid).get());
        like.setVideoId(vRepo.findById(vid).get());
        lRepo.save(like);
    }

    public void disLike(int uid,int vid) {
        lRepo.delete(lRepo.findByUserIdIdAndVideoIdId(uid,vid));
    }
}
