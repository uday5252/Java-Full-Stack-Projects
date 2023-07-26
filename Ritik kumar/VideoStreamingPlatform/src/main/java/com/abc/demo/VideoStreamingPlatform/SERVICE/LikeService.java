package com.abc.demo.VideoStreamingPlatform.SERVICE;

import com.abc.demo.VideoStreamingPlatform.ENTITY.LikeEntity;
import com.abc.demo.VideoStreamingPlatform.ENTITY.UserEntity;
import com.abc.demo.VideoStreamingPlatform.ENTITY.VideoEntity;
import com.abc.demo.VideoStreamingPlatform.REPOSITORY.LikeRepositoryInterface;
import com.abc.demo.VideoStreamingPlatform.REPOSITORY.UserRepositoryInterface;
import com.abc.demo.VideoStreamingPlatform.REPOSITORY.VideoRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    LikeRepositoryInterface lri;

    @Autowired
    UserRepositoryInterface uri;

    @Autowired
    VideoRepositoryInterface vri;

    @Autowired
    KafkaTemplate<Integer,String> kt;

    public void addLikeToVideo(LikeEntity le)
    {
        UserEntity u=uri.findById(le.getUserId()).get();
        VideoEntity v=vri.findById(le.getVideoId()).get();
        String likeMessage=v.getVideoTitle()+" video is liked By "+u.getUserName();
        lri.save(le);
        kt.send("likeTopic",likeMessage);
    }

}
