package com.xyz.demo.EndProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.xyz.demo.EndProject.Entity.Like;
import com.xyz.demo.EndProject.Entity.UserEntity;
import com.xyz.demo.EndProject.Entity.VideoEntity;
import com.xyz.demo.EndProject.Interface.LikeRepo;
import com.xyz.demo.EndProject.Interface.UserRepo;
import com.xyz.demo.EndProject.Interface.VideoRepo;

@Service
public class LikeService {
    @Autowired
    LikeRepo lr;
    @Autowired
    UserRepo ur;
    @Autowired
    VideoRepo vr;

    @Autowired
	KafkaTemplate<Integer, String> kt;

    public String createLike(int videoId, int userId) {
        VideoEntity video = vr.findById(videoId).get();
        UserEntity user = ur.findById(userId).get();
        
        Like likes = lr.findByUserUserIdAndVideoVideoId(userId, videoId);
        if (likes == null) 
        {
            Like like = new Like();
            like.setUser(user);
            like.setVideo(video);
            lr.save(like);
            Like l = lr.findById(like.getLikeId()).get();
            kt.send("user_likes",l.toString());
            return "Liked Successfully";
        } 
        else 
        {
            return "One user cannot be Liked more than once";
        }
    }

    public String deleteLike(int videoId, int userId) 
    {
//        VideoEntity video = vr.findById(videoId).get();
//        UserEntity user = ur.findById(userId).get();
        
        Like likes = lr.findByUserUserIdAndVideoVideoId(userId, videoId);
        if (likes != null) 
        {
            lr.delete(likes);
            return "Like Deleted";
        }
        else 
        {
            return "Like already Deleted";
        }
    }

    public List<Like> getAllLikes() {
        return lr.findAll();
    }
}
