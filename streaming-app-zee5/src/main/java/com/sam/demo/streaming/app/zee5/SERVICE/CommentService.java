package com.sam.demo.streaming.app.zee5.SERVICE;



import com.sam.demo.streaming.app.zee5.ENTITY.CommentEntity;
import com.sam.demo.streaming.app.zee5.ENTITY.UserEntity;
import com.sam.demo.streaming.app.zee5.ENTITY.VideoEntity;
import com.sam.demo.streaming.app.zee5.REPOSITORY.CommentRepository;
import com.sam.demo.streaming.app.zee5.REPOSITORY.UserRepository;
import com.sam.demo.streaming.app.zee5.REPOSITORY.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        comment.setUserId(user_id);
        comment.setVideoId(video_id);



        cr.save(comment);
        return cr.findById((long) comment.getId()).get();
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
