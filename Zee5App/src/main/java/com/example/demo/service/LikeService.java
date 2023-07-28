package com.example.demo.service;

//import com.example.demo.entity.LikeEntity;
import com.example.demo.entity.LikeEntity;
import com.example.demo.entity.User;
import com.example.demo.entity.Video;
//import com.example.demo.repository.LikeRepository;
import com.example.demo.kafka.KafkaProducer;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    LikeRepository lri;

    @Autowired
    UserRepository uri;

    @Autowired
    VideoRepository vri;

    @Autowired
    KafkaProducer kp;

    public void collectLikeDetails(LikeEntity likeEntity) {
        User user=uri.findById(likeEntity.getUserid().getUid()).get();
        Video video=vri.findById(likeEntity.getVideoid().getVid()).get();
        likeEntity.setVideoid(video);
        likeEntity.setUserid(user);
        lri.save(likeEntity);
        kp.sendToTopic("user_likes","Video "+video.getVid()+" is liked by User "+user.getUid());

    }


}
