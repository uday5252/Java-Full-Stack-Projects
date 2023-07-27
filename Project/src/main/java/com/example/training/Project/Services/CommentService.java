package com.example.training.Project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.training.Project.Entities.CommentEntity;
import com.example.training.Project.Repositories.CommentRepo;
import com.example.training.Project.Repositories.UserRepo;
import com.example.training.Project.Repositories.VideoRepo;

@Service
public class CommentService {

    @Autowired
    CommentRepo cRepo;

    @Autowired
    UserRepo uRepo;

    @Autowired
    VideoRepo vRepo;

    public void setComment(CommentEntity comment,int uid,int vid){
        comment.setUserId(uRepo.findById(uid).get());
        comment.setVideoId(vRepo.findById(vid).get());
        cRepo.save(comment);
    }

    public void unComment(int vid,int uid){
        cRepo.delete(cRepo.findByVideoIdIdAndUserIdId(vid,uid));
    }
}
