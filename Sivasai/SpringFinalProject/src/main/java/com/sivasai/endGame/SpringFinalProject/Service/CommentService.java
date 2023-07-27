package com.sivasai.endGame.SpringFinalProject.Service;

import com.sivasai.endGame.SpringFinalProject.Entity.Comments;
import com.sivasai.endGame.SpringFinalProject.Entity.User;
import com.sivasai.endGame.SpringFinalProject.Entity.Video;
import com.sivasai.endGame.SpringFinalProject.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Comments createComment(Comments comment){
        return commentRepository.save(comment);
    }

    public List<Comments> getAllVideoComments(Video video){
        return commentRepository.findByVideoId(video);
    }

    public Boolean checkIfExists(Video video){
        return commentRepository.existsByVideoId(video);
    }

    //is int id enough or should i give the whole object?
    public void deleteComment(User user, Video video){
        commentRepository.deleteByUserIdAndVideoId(user, video);
    }

    public Comments updateComment(User user, Video video, String content){
        Comments existingComment = commentRepository.findByUserIdAndVideoId(user, video).get();
        if(content != null){
            existingComment.setContent(content);
        }
        return commentRepository.save(existingComment);
    }

    public Boolean checkIfExistsByUserIdAndVideoId(User u, Video v){
        return commentRepository.existsByUserIdAndVideoId(u, v);
    }

    public Boolean checkIfExistsById(int commentId){
        return commentRepository.existsById(commentId);
    }

    public void deleteById(int commentId){
        commentRepository.deleteById(commentId);
    }

    public Comments updateCommentById(int commentId, String content){
        Comments existingComment = commentRepository.findById(commentId).get();
        if(content != null){
            existingComment.setContent(content);
        }
        return commentRepository.save(existingComment);
    }

}
