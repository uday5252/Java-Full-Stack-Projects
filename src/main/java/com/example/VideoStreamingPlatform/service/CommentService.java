package com.example.VideoStreamingPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.VideoStreamingPlatform.entity.Comment;
import com.example.VideoStreamingPlatform.entity.User;
import com.example.VideoStreamingPlatform.entity.Video;
import com.example.VideoStreamingPlatform.repository.CommentRepository;
import com.example.VideoStreamingPlatform.repository.UserRepository;
import com.example.VideoStreamingPlatform.repository.VideoRepository;

@Service
public class CommentService {
  @Autowired
  CommentRepository cr;
  @Autowired
  UserRepository ur;
  @Autowired
  VideoRepository vr;

  public Comment createComment(int video_id, int user_id, Comment commentObj) {
    User commentedBy = ur.findById(user_id).get();
    Video video = vr.findById(video_id).get();
    commentObj.setCommentor(commentedBy);
    commentObj.setVideo(video);
    cr.save(commentObj);
    return cr.findById(commentObj.getComment_id()).get();
  }

  public String getCommentor(int user_id) {
    return ur.findById(user_id).get().getUsername();
  }

  public String getVideoTitle(int video_id) {
    return vr.findById(video_id).get().getTitle();
  }

}
