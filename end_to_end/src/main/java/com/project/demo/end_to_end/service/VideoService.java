package com.project.demo.end_to_end.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.end_to_end.entities.Video;
import com.project.demo.end_to_end.repository.VideoRepositoryInterface;

@Service
public class VideoService {
    @Autowired
    VideoRepositoryInterface vri;

    public void addVideo(Video v) {
        vri.save(v);
    }

    public Video getVideo(int id){
        return vri.findById(id).get();
    }

    public List<Video> getAllVideos() {
        return vri.findAll();
    }

    public List<Video> findByGenreId(int genreId) {
        return vri.findByGenreId(genreId);
    }

    public void deleteVideo(int videoId) {
        vri.deleteById(videoId);
    }
}
