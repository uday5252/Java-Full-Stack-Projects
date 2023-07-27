package com.zee.Zee5Clone.Service;

import com.zee.Zee5Clone.Entity.CommentEntity;
import com.zee.Zee5Clone.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired

    KafkaTemplate<Integer,String> kt;

    public void addComment(CommentEntity comment,int userId,int videoId){
        comment.setUserId(userId);
        comment.setVideoId(videoId);
        commentRepository.save(comment);
        kt.send("user_comments","Comment : " + comment.getComment() + " has been added by " + userId+ "on videoId "+ videoId);
    }
    public List<CommentEntity> showAllComment(){
        List<CommentEntity> comments = commentRepository.findAll();
        return comments;
    }
}
