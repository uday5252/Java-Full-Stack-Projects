package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.LikeEntity;
import com.example.demo.entity.User;
import com.example.demo.entity.Video;
import com.example.demo.kafka.KafkaProducer;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository cri;

    @Autowired
    UserRepository uri;

    @Autowired
    VideoRepository vri;

    @Autowired
    KafkaProducer kp;
    public void collectCommentDetails(Comment comment) {
        User user=uri.findById(comment.getUserid().getUid()).get();
        Video video=vri.findById(comment.getVideoid().getVid()).get();
        comment.setVideoid(video);
        comment.setUserid(user);
        cri.save(comment);
        kp.sendToTopic("user_comments","\""+comment.getDescription()+"\" is commented by user"+user.getUid()+" for the video "+video.getVid());
    }
}
