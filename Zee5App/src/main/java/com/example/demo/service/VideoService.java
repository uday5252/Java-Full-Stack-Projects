package com.example.demo.service;


import com.example.demo.dao.VideoDao;
import com.example.demo.entity.Video;
import com.example.demo.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    VideoDao videoDao;
    public void collectVideoDetails(Video video) {

      videoDao.saveVideoDetails(video);
    }

    public Video retrieveVideoDetails(int vid) {
       return  videoDao.getVideoDaoDetails(vid);
    }

    public List<Video> retrieveAllVideoDetails() {
        return videoDao.getAllVideoDaoDetails();
    }

    public void deleteVideoById(int vid) {
        videoDao.deleteVideoById(vid);
    }


}
