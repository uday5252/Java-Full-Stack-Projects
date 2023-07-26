package com.example.VideoStreamingPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.VideoStreamingPlatform.entity.Likes;
import com.example.VideoStreamingPlatform.entity.User;
import com.example.VideoStreamingPlatform.entity.Video;
import com.example.VideoStreamingPlatform.repository.LikeRepository;
import com.example.VideoStreamingPlatform.repository.UserRepository;
import com.example.VideoStreamingPlatform.repository.VideoRepository;

@Service
public class LikeService {
  @Autowired
  LikeRepository lr;
  @Autowired
  UserRepository ur;
  @Autowired
  VideoRepository vr;

  public Likes createLike(int video_id, int user_id, Likes likeObj) {
    User likedBy = ur.findById(user_id).get();
    Video video = vr.findById(video_id).get();
    likeObj.setLiked_by(likedBy);
    likeObj.setVideo(video);
    lr.save(likeObj);
    return lr.findById(likeObj.getLike_id()).get();
  }

  public String getLikedBy(int user_id) {
    return ur.findById(user_id).get().getUsername();
  }

  public String getVideoTitle(int video_id) {
    return vr.findById(video_id).get().getTitle();
  }
}
