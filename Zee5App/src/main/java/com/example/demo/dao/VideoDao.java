package com.example.demo.dao;


import com.example.demo.entity.Genre;
import com.example.demo.entity.User;
import com.example.demo.entity.Video;
import com.example.demo.kafka.KafkaProducer;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VideoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoDao {
    @Autowired
    VideoRepository vri;

    @Autowired
    GenreRepository gri;

    @Autowired
    UserRepository uri;

    @Autowired
    KafkaProducer kp;

    public void saveVideoDetails(Video video) {
       String uploadedBy= video.getUploaded_by().getName();
       User uploadedUser=uri.findByName(uploadedBy);
       video.setUploaded_by(uploadedUser);
       int gid = video.getGenre_id().getGid();
       Genre genre =gri.findById(gid).get();
       video.setGenre_id(genre);
       vri.save(video);
       kp.sendToTopic("video_uploads","Video \""+video.getUrl()+"\" is uploaded by user "+uploadedBy);

    }

    public Video getVideoDaoDetails(int vid) {
        return vri.findById(vid).get();
    }

    public List<Video> getAllVideoDaoDetails() {
        return vri.findAll();
    }

    public void deleteVideoById(int vid) {
        vri.deleteById(vid);
    }
}
