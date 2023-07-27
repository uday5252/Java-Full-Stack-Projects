package com.abc.demo.Zee5.Apache.Kafka.Producer.SERVICE;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.CommentEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.UserEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.VideoEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY.CommentRepository;
import com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY.UserRepository;
import com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VideoRepository videoRepository;

    public CommentEntity addComment(int userId, int videoId, CommentEntity comment){
        UserEntity user = userRepository.findById(userId).get();
        comment.setUser(user);
        VideoEntity video = videoRepository.findById(videoId).get();
        comment.setVideo(video);
        LocalDateTime now = LocalDateTime.now();
        comment.setCommented_on(now);
        commentRepository.save(comment);
        return commentRepository.findById(comment.getComment_id()).get();
    }

    public String commentedBy(int user_id){
        UserEntity user = userRepository.findById(user_id).get();
        return user.getUser_name();
    }

    public String commentedVideo(int video_id){
        VideoEntity video = videoRepository.findById(video_id).get();
        return video.getVideo_title();
    }
}
