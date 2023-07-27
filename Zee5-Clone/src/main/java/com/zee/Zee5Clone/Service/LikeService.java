package com.zee.Zee5Clone.Service;

import com.zee.Zee5Clone.Entity.LikeEntity;
import com.zee.Zee5Clone.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    KafkaTemplate<Integer,String> kt;
    public void addLike(int videoId,int userId){
        LikeEntity Like = new LikeEntity(userId,videoId);
        likeRepository.save(Like);
        kt.send("user_likes","video with video Id " +Like.getVideoId() + " has been liked by user Id " + Like.getUserId());
    }
    public List<LikeEntity> getLike(){
        List<LikeEntity> likes = likeRepository.findAll();
        return likes;
    }
}
