package com.zee.phani.project.ott.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zee.phani.project.ott.dao.VideoDao;
import com.zee.phani.project.ott.dto.VideoRequestDto;
import com.zee.phani.project.ott.dto.VideoResponseDto;
import com.zee.phani.project.ott.kafka.KafkaProducer;

@Service
public class VideoService {

    @Autowired
    VideoDao videoDao;

    @Autowired
    KafkaProducer kp;

    String topic = "video_uploads";

    public void uploadVideo(VideoRequestDto inVideoRequestDto)
            throws NoSuchElementException, IllegalArgumentException, JsonProcessingException {
        if (Objects.isNull(inVideoRequestDto))
            throw new IllegalArgumentException("The request body cannot be null");

        VideoResponseDto vDto = videoDao.createVideoEntry(inVideoRequestDto);
        kp.UploadToTopic(topic, vDto.getVideo());

    }

    public VideoResponseDto getOneVideo(int videoId) throws NoSuchElementException {
        return videoDao.getSingleVideo(videoId);
    }

    public List<VideoResponseDto> getAllVideos() {
        return videoDao.getAllVideos();
    }

    public List<VideoResponseDto> getVideosFromGenre(int genreId) {
        return videoDao.getAllVideosfromGenre(genreId);
    }

    public void updateVideo(VideoRequestDto videoRequestDto, int videoId) throws NoSuchElementException {
        videoDao.updateVideo(videoRequestDto, videoId);
    }

    public void deleteVideo(int videoId) throws NoSuchElementException, Exception {
        try {
            videoDao.deleteVideo(videoId);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("The video with the given id is not present");
        } catch (Exception e) {
            throw e;
        }
    }
}
