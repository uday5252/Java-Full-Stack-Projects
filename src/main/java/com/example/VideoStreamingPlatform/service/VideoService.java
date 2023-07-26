package com.example.VideoStreamingPlatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.VideoStreamingPlatform.entity.Genre;
import com.example.VideoStreamingPlatform.entity.User;
import com.example.VideoStreamingPlatform.entity.Video;
import com.example.VideoStreamingPlatform.repository.GenreRepository;
import com.example.VideoStreamingPlatform.repository.UserRepository;
import com.example.VideoStreamingPlatform.repository.VideoRepository;

@Service
public class VideoService {
  @Autowired
  VideoRepository vr;
  @Autowired
  UserRepository ur;
  @Autowired
  GenreRepository gr;

  public Video createVideo(int user_id, int genre_id, Video videoObj) {
    User uploader = ur.findById(user_id).get();
    Genre genre = gr.findById(genre_id).get();
    videoObj.setGenre(genre);
    videoObj.setUploader(uploader);
    vr.save(videoObj);
    return vr.findById(videoObj.getVideo_id()).get();
  }

  public List<Video> getAllVideos() {
    return vr.findAll();
  }

  public Video getVideo(int video_id) {
    Video video = vr.findById(video_id).get();
    return video;
  }

  public List<Video> getVideosByGenre(int genre_id) {
    Genre genre = gr.findById(genre_id).get();
    List<Video> videos = vr.findAllByGenre(genre);
    return videos;
  }

  public Video updateVideo(int video_id, Video videoObj) {
    Video orgVideo = vr.findById(video_id).get();
    orgVideo.setTitle(videoObj.getTitle() == null ? orgVideo.getTitle() : videoObj.getTitle());
    orgVideo.setDescription(videoObj.getDescription() == null ? orgVideo.getDescription() : videoObj.getDescription());
    orgVideo.setUrl(videoObj.getUrl() == null ? orgVideo.getUrl() : videoObj.getUrl());
    vr.save(orgVideo);
    return orgVideo;
  }

  public void deleteVideo(int video_id) {
    Video orgVideo = vr.findById(video_id).get();
    vr.delete(orgVideo);
  }

  public String getVideoUrl(int video_id) {
    Video response = vr.findById(video_id).get();
    String url = response.getUrl();
    return url;
  }

  public String getUploader(int user_id) {
    return ur.findById(user_id).get().getUsername();
  }

}
