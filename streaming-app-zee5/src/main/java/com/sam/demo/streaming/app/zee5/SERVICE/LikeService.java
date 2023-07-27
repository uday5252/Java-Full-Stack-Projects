package com.sam.demo.streaming.app.zee5.SERVICE;


import com.sam.demo.streaming.app.zee5.ENTITY.LikeEntity;
import com.sam.demo.streaming.app.zee5.ENTITY.UserEntity;
import com.sam.demo.streaming.app.zee5.ENTITY.VideoEntity;
import com.sam.demo.streaming.app.zee5.REPOSITORY.LikeRepository;
import com.sam.demo.streaming.app.zee5.REPOSITORY.UserRepository;
import com.sam.demo.streaming.app.zee5.REPOSITORY.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

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
        liked_by.setVideoId(video_id);
        liked_by.setUserId( user_id);



        lr.save(liked_by);
        return lr.findById(liked_by.getId()).get();

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


