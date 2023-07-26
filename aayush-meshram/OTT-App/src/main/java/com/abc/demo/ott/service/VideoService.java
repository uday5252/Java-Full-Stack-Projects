package com.abc.demo.ott.service;

import com.abc.demo.ott.entity.GenreEntity;
import com.abc.demo.ott.entity.VideoEntity;
import com.abc.demo.ott.helper.CurrentDateHelper;
import com.abc.demo.ott.repository.GenreRepositoryInterface;
import com.abc.demo.ott.repository.UserRepositoryInterface;
import com.abc.demo.ott.repository.VideoRepositoryInterface;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VideoService {

    @Autowired
    VideoRepositoryInterface vri;

    @Autowired
    GenreRepositoryInterface gri;

    @Autowired
    UserRepositoryInterface uri;

    @Autowired
    KafkaTemplate<String, String> kt;

    public void addVideo(VideoEntity videoEntity)  {
        videoEntity.setVideoCreatedAt(CurrentDateHelper.now());
        Set<GenreEntity> set = new HashSet<>();
        for(GenreEntity genre: videoEntity.getVideoGenreID())   {
            genre = gri.findByGenreName(genre.getGenreName());
            set.add(genre);
        }

        String message = uri.findById(videoEntity.getVideoUploadedBy()).get().getUserName()
                +"added video titled: "
                + videoEntity.getVideoTitle();

        videoEntity.setVideoGenreID(set);
        vri.save(videoEntity);

        kt.send("video_uploads", message);
    }

    public VideoEntity getVideoDetails(int videoID)   {
        return vri.findById(videoID).get();
    }

    public List<VideoEntity> getAllVideos() {
        return vri.findAll();
    }

    public List<VideoEntity> getAllVideoByGenre(int genreID)   {
        List<VideoEntity> all = vri.findAll();
        List<VideoEntity> genreVideo = new ArrayList<>();
        for (VideoEntity entity : all)  {
            if(entity.getVideoGenreID().contains(gri.findById(genreID).get())){
                genreVideo.add(entity);
            }
        }
        return genreVideo;
    }

    public void updateVideoDetails(int videoID, VideoEntity newDetails)    {
        VideoEntity currentDetails = vri.findById(videoID).get();
        currentDetails.setVideoTitle((newDetails.getVideoTitle() != null)? newDetails.getVideoTitle() : currentDetails.getVideoTitle());
        currentDetails.setVideoDescription((newDetails.getVideoDescription() != null)? newDetails.getVideoDescription() : currentDetails.getVideoDescription());
        currentDetails.setVideoURL((newDetails.getVideoURL() != null)? newDetails.getVideoURL() : currentDetails.getVideoURL());
        currentDetails.setVideoGenreID((newDetails.getVideoGenreID() != null)? newDetails.getVideoGenreID() : currentDetails.getVideoGenreID());
        currentDetails.setVideoUploadedBy((newDetails.getVideoUploadedBy() != 0)? newDetails.getVideoUploadedBy() : currentDetails.getVideoUploadedBy());
        vri.save(currentDetails);
    }

    public void deleteVideo(int videoID)   {
        vri.delete(vri.findById(videoID).get());
    }

}
