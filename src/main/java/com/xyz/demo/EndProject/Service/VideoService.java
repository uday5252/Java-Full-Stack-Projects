package com.xyz.demo.EndProject.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.xyz.demo.EndProject.Entity.GenreEntity;
import com.xyz.demo.EndProject.Entity.UserEntity;
import com.xyz.demo.EndProject.Entity.VideoEntity;
import com.xyz.demo.EndProject.Interface.GenreRepo;
import com.xyz.demo.EndProject.Interface.UserRepo;
import com.xyz.demo.EndProject.Interface.VideoRepo;


@Service
public class VideoService 
{
    @Autowired
    VideoRepo vr;
    @Autowired
    GenreRepo gr;
    @Autowired
    UserRepo ur;
    
    @Autowired
    KafkaTemplate<Integer, String> kt;
    
    public void uploadNewVideo(VideoEntity videoEntity,int uid,int gid) 
    {
        GenreEntity genre = gr.findById(gid).get();
        UserEntity user=ur.findById(uid).get();
        
        videoEntity.setGenreId(genre);
        videoEntity.setUploaded_by(user);
        vr.save(videoEntity);
        VideoEntity v = vr.findById(videoEntity.getVideoId()).get();
        kt.send("video_uploads",v.toString());

    }
           
    public VideoEntity getVideoById(int videoId) {
        return vr.findById(videoId).orElse(null);
    }

    public List<VideoEntity> getAllVideos() {
        return vr.findAll();
    }

    public List<VideoEntity> getVideosByGenre(int genreId) {
        GenreEntity genre = gr.findById(genreId).get();
        if (genre != null) {
            return vr.findByGenreId(genre);
        } else {
            return new ArrayList<>();
        }
    }

    public VideoEntity updateVideo(int videoId, VideoEntity video) {
        VideoEntity existingVideo = vr.findById(videoId).orElse(null);
        if (existingVideo != null) {
            existingVideo.setTitle(video.getTitle());
            existingVideo.setDescription(video.getDescription());
            existingVideo.setUrl(video.getUrl());
            return vr.save(existingVideo);
        } else {
            return null;
        }
    }

    public boolean deleteVideo(int videoId) {
        if (vr.existsById(videoId)) {
            vr.deleteById(videoId);
            return true;
        } else {
            return false;
        }
    }

	public List<VideoEntity> getAllVideo() 
	{
	    List<VideoEntity> ve=vr.findAll();
	    return ve;
	}
    
 }

