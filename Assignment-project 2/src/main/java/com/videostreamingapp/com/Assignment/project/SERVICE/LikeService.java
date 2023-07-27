package com.videostreamingapp.com.Assignment.project.SERVICE;

import com.videostreamingapp.com.Assignment.project.ENTITY.LikeEntity;
import com.videostreamingapp.com.Assignment.project.ENTITY.UserEntity;
import com.videostreamingapp.com.Assignment.project.ENTITY.VideoEntity;
import com.videostreamingapp.com.Assignment.project.REPOSITORY.LikeRepository;
import com.videostreamingapp.com.Assignment.project.REPOSITORY.UserRepository;
import com.videostreamingapp.com.Assignment.project.REPOSITORY.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeService {
    @Autowired
    LikeRepository lr;

    @Autowired
    UserRepository ur;

    @Autowired
    VideoRepository vr;

    public LikeEntity addlike(int user_id, int video_id, LikeEntity liked_by){
        VideoEntity video = vr.findById(video_id).get();
        UserEntity user = ur.findById(user_id).get();
        liked_by.setVideo(video);
        liked_by.setUser(user);

        LocalDateTime now = LocalDateTime.now();
        liked_by.setLike_created_at(now);

        lr.save(liked_by);
        return lr.findById(liked_by.getLike_id()).get();

    }

    public void deleteLike(int video_id, int user_id){
        VideoEntity video = vr.findById(video_id).get();
        UserEntity user = ur.findById(user_id).get();
        LikeEntity liked_by = lr.findByVideoAndUser(video,user);
        lr.deleteById(liked_by.getLike_id());
       // return lr.findById(liked_by.getLike_id()).get();
    }

    public String likedBy(int user_id){
        UserEntity user = ur.findById(user_id).get();
        return user.getUsername();
    }
    public String likedVideo(int video_id){
        VideoEntity video = vr.findById(video_id).get();
        return video.getTitle();
    }




}
