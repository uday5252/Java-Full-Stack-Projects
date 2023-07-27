package com.abc.demo.Zee5.Apache.Kafka.Producer.SERVICE;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.LikeEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.UserEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.VideoEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY.LikeRepository;
import com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY.UserRepository;
import com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeService {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VideoRepository videoRepository;

    public void addLike(int userId, int videoId, LikeEntity like){
        UserEntity user = userRepository.findById(userId).get();
        like.setUser(user);
        VideoEntity video = videoRepository.findById(videoId).get();
        like.setVideo(video);
        LocalDateTime now = LocalDateTime.now();
        like.setLiked_on(now);
        likeRepository.save(like);
    }

    public String likedBy(int user_id){
        UserEntity user = userRepository.findById(user_id).get();
         return user.getUser_name();
    }

    public String likedVideo(int video_id){
        VideoEntity video = videoRepository.findById(video_id).get();
        return video.getVideo_title();
    }
}
