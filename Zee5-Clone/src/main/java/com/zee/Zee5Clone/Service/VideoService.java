package com.zee.Zee5Clone.Service;

import com.zee.Zee5Clone.Entity.Video;
import com.zee.Zee5Clone.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;



@Service
public class VideoService {
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    KafkaTemplate<Integer,String> kt;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String time = dtf.format(now);
    public Video uploadVideo(Video video){
        video.setUploadedAt(time);
        kt.send("video_uploads","video has been uploaded by" + video.getUploadedBy());
        return videoRepository.save(video);

    }

    public Video getVideo(int videoId){
        Video video =videoRepository.findById(videoId).get();
        return video;
    }

    public List<Video> getVideo(){
        List<Video> videos = videoRepository.findAll();
        return videos;
    }

    public List<Video> getVideoByGenre(int genreId){
        List<Video> videos =videoRepository.findBygenreId(genreId);
        return videos;
    }

    public List<Video> updateVideo(Video video,int videoId){

        Video oldVideo = videoRepository.findById(videoId).get();
        if(video.getTitle()!=null){
            oldVideo.setTitle(video.getTitle());
        }
        if(video.getUrl()!=null){
            oldVideo.setUrl(video.getUrl());
        }
        if(video.getDescription()!=null){
            oldVideo.setDescription(video.getDescription());
        }
        if(video.getGenreId()!=null){
            oldVideo.setGenreId(video.getGenreId());
        }
        video.setUploadedAt(time);
        videoRepository.save(oldVideo);

        List<Video> videos = videoRepository.findAll();
        return videos;
    }

    public void deleteVideo(int videoId){
        videoRepository.deleteById(videoId);
    }






}
