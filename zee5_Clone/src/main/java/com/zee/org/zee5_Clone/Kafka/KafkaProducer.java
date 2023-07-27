package com.zee.org.zee5_Clone.Kafka;

import com.zee.org.zee5_Clone.Entity.*;
import com.zee.org.zee5_Clone.Repository.CommentRepository;
import com.zee.org.zee5_Clone.Repository.GenreRepository;
import com.zee.org.zee5_Clone.Repository.Likerepository;
import com.zee.org.zee5_Clone.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer
{
    @Autowired
    KafkaTemplate<Integer,String> Kt;

    @Autowired
    VideoRepository Vr;
    @Autowired
    Likerepository Lr;
    @Autowired
    CommentRepository Cr;
    @Autowired
    GenreRepository Gr;

    public String sendUserDataToTopic(int videoid){
        VideoTable v=Vr.findById(videoid).get();
        String dataUrl=v.getUrl();
        Kt.send("video_uploads",dataUrl+" : is uploaded by User "+v.getUser().getUsername());
        return dataUrl;
    }

    public int sendLikeDataToTopic(int likeid){
        LikeTable l=Lr.findById(likeid).get();
        int vid=l.getVideoid().getVideoid();

        Kt.send("user_likes","VideoId "+vid+" : is liked by User "+l.getUserid().getUsername());
        return vid;

    }
    public int sendCommentDataToTopic(int comm_id){
        CommentTable c=Cr.findById(comm_id).get();
        int cid=c.getVideo().getVideoid();

        Kt.send("user_comments","Comment ["+c.getComment()+"] is given by "+c.getUser().getUsername());
        return cid;

    }

    public GenreTable sendGenreDataToTopic(int gid){
        GenreTable g=Gr.findById(gid).get();

        Kt.send("genre_updates","Changes made are "+g.getDiscription()+","+g.getName());
        return g;

    }

}
