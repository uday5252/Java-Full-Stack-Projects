package com.xyz.demo.EndProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.xyz.demo.EndProject.Entity.Comment;
import com.xyz.demo.EndProject.Entity.UserEntity;
import com.xyz.demo.EndProject.Entity.VideoEntity;
import com.xyz.demo.EndProject.Interface.CommentRepo;
import com.xyz.demo.EndProject.Interface.UserRepo;
import com.xyz.demo.EndProject.Interface.VideoRepo;

@Service
public class CommentService 
{
	 @Autowired
	    private CommentRepo cr;

	    @Autowired
	    private UserRepo ur;

	    @Autowired
	    private VideoRepo vr;
	    
	    @Autowired
		KafkaTemplate<Integer, String> kt;


    public String addComment(int userId, int videoId,Comment c) 
    {
        UserEntity user = ur.findById(userId).get();
       
        VideoEntity video = vr.findById(videoId).get();
       
        Comment commentEntity = new Comment();
        
        commentEntity.setUser(user);
        commentEntity.setVideo(video);
        commentEntity.setComment(c.getComment());
        cr.save(commentEntity);
        
        Comment c1 = cr.findById(commentEntity.getCommentId()).get();
        kt.send("user_comments",c1.toString());


        return "Comment added";
    }

    public String deleteComment(int commentId) 
    {
        Comment commentEntity = cr.findById(commentId).orElse(null);
        if (commentEntity == null) {
            return "Comment does not exist";
        }

        cr.delete(commentEntity);
        return "Comment is deleted";
    }

    public List<Comment> getAllComments() {
        return cr.findAll();
    }

    public String updateComment(int commentId, String newCommentText) {
        Comment commentEntity = cr.findById(commentId).orElse(null);
        if (commentEntity == null) {
            return "Comment does not exist";
        }

        commentEntity.setComment(newCommentText);
        cr.save(commentEntity);
        return "Comment updated";
    }
}
