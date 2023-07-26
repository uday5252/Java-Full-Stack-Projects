package com.abc.demo.VideoStreamingPlatform.SERVICE;

import com.abc.demo.VideoStreamingPlatform.ENTITY.GenreEntity;
import com.abc.demo.VideoStreamingPlatform.ENTITY.VideoEntity;
import com.abc.demo.VideoStreamingPlatform.REPOSITORY.GenreRepositoryInterface;
import com.abc.demo.VideoStreamingPlatform.REPOSITORY.VideoRepositoryInterface;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    VideoRepositoryInterface vri;

    @Autowired
    GenreRepositoryInterface gri;

    @Autowired
    KafkaTemplate<Integer,String> kt;

    public VideoEntity addVideo(VideoEntity video,int gId)
    {
        GenreEntity g=gri.findById(gId).get();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        video.setVideoUploadedAt(date);
        video.setGenre(g);
        vri.save(video);

        String videoMessage="new Video is uploaded";
        kt.send("videoTopic",videoMessage);
        return video;
    }

    public VideoEntity getVideo(int videoId)
    {
        return vri.findById(videoId).get();
    }

    public List<VideoEntity> allVideo()
    {
        return vri.findAll();
    }

    public List<VideoEntity> getVideoByGenre(int gId)
    {
        GenreEntity g=gri.findById(gId).get();
        List<VideoEntity> videos=vri.findAllByGenre(g);
        return videos;
    }

    public void updateVideoDetails(VideoEntity updatedData,int videoId)
    {
        VideoEntity videoData=vri.findById(videoId).get();
        videoData.setVideoTitle(updatedData.getVideoTitle() != null ? updatedData.getVideoTitle() : videoData.getVideoTitle());
        videoData.setVideoDescription(updatedData.getVideoDescription() != null ? updatedData.getVideoDescription() : videoData.getVideoDescription());
        videoData.setVideoUrl(updatedData.getVideoUrl() != null ? updatedData.getVideoUrl() : videoData.getVideoUrl());
        videoData.setVideoUploadedBy(updatedData.getVideoUploadedBy() != 0 ? updatedData.getVideoUploadedBy() : videoData.getVideoUploadedBy());
        vri.save(videoData);
    }

    public void deleteVideo(VideoEntity video)
    {
        vri.delete(video);
    }
}
