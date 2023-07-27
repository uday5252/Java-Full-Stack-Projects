package com.videostreamingapp.com.Assignment.project.SERVICE;

import com.videostreamingapp.com.Assignment.project.ENTITY.CommentEntity;
import com.videostreamingapp.com.Assignment.project.ENTITY.UserEntity;
import com.videostreamingapp.com.Assignment.project.ENTITY.VideoEntity;
import com.videostreamingapp.com.Assignment.project.REPOSITORY.CommentRepository;
import com.videostreamingapp.com.Assignment.project.REPOSITORY.UserRepository;
import com.videostreamingapp.com.Assignment.project.REPOSITORY.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    CommentRepository cr;
    @Autowired
    VideoRepository vr;
    @Autowired
    UserRepository ur;

    public CommentEntity addComment(int user_id,int video_id, CommentEntity comment){
        UserEntity user = ur.findById(user_id).get();
        VideoEntity video = vr.findById(video_id).get();
        comment.setUser(user);
        comment.setVideo(video);

        LocalDateTime now = LocalDateTime.now();
        comment.setLike_created_at(now);

        cr.save(comment);
        return cr.findById(comment.getComment_id()).get();
    }
    public CommentEntity deleteComment(int video_id, int user_id){
        VideoEntity video = vr.findById(video_id).get();
        UserEntity user = ur.findById(user_id).get();
        CommentEntity comment = cr.findByVideoAndUser(video,user);
        cr.deleteById(comment.getComment_id());
        return cr.findById(comment.getComment_id()).get();
    }
    public String commentedBy(int user_id){
        UserEntity user = ur.findById(user_id).get();
        return user.getUsername();
    }
    public String commentedVideo(int video_id){
        VideoEntity video = vr.findById(video_id).get();
        return video.getTitle();
    }

}
