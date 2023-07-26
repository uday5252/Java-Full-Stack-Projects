package com.abc.demo.VideoStreamingPlatform.SERVICE;

import com.abc.demo.VideoStreamingPlatform.ENTITY.CommentEntity;
import com.abc.demo.VideoStreamingPlatform.ENTITY.UserEntity;
import com.abc.demo.VideoStreamingPlatform.ENTITY.VideoEntity;
import com.abc.demo.VideoStreamingPlatform.REPOSITORY.CommentRepositoryInterface;
import com.abc.demo.VideoStreamingPlatform.REPOSITORY.UserRepositoryInterface;
import com.abc.demo.VideoStreamingPlatform.REPOSITORY.VideoRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepositoryInterface cri;

    @Autowired
    UserRepositoryInterface uri;

    @Autowired
    VideoRepositoryInterface vri;

    @Autowired
    KafkaTemplate<Integer,String> kt;

    public CommentEntity addCommentToVideo(CommentEntity ce)
    {
        UserEntity u= uri.findById(ce.getUserId()).get();
        VideoEntity v=vri.findById(ce.getVideoId()).get();
        String commentMessage="Comment is done By "+u.getUserName()+" to the video "+v.getVideoTitle();
        cri.save(ce);
        kt.send("commentTopic",commentMessage);
        return ce;
    }
}
