package com.zee.phani.project.ott.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zee.phani.project.ott.dto.VideoRequestDto;
import com.zee.phani.project.ott.dto.VideoResponseDto;
import com.zee.phani.project.ott.entity.GenreEntity;
import com.zee.phani.project.ott.entity.UserEntity;
import com.zee.phani.project.ott.entity.VideoEntity;
import com.zee.phani.project.ott.repository.GenreRepository;
import com.zee.phani.project.ott.repository.UserRepository;
import com.zee.phani.project.ott.repository.VideoRepository;

@Component
public class VideoDao {
    @Autowired
    VideoRepository repo;

    @Autowired
    GenreRepository genRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ModelMapper mapper;

    public VideoResponseDto createVideoEntry(VideoRequestDto inVideoDto) {

        VideoEntity videoEntity = mapper.map(inVideoDto, VideoEntity.class);
        UserEntity userEntity;
        GenreEntity genreEntity;
        try {
            userEntity = userRepo.findById(inVideoDto.getUserId()).get();
        } catch (Exception e) {
            throw new NoSuchElementException("The User With the given USER ID is not present");
        }

        try {
            genreEntity = genRepo.findById(inVideoDto.getGenreId()).get();
        } catch (Exception e) {
            throw new NoSuchElementException("The Genre With the given GENRE ID is not present");
        }

        videoEntity.setGenre(genreEntity);
        videoEntity.setUser(userEntity);

        videoEntity = repo.save(videoEntity);
        VideoResponseDto videoResponseDto = mapper.map(videoEntity, VideoResponseDto.class);
        videoResponseDto.setVideo(videoEntity);
        return videoResponseDto;

    }

    public VideoResponseDto getSingleVideo(int videoId) {
        try {
            VideoEntity ve = repo.findById(videoId).get();
            VideoResponseDto vResponseDto = mapper.map(ve, VideoResponseDto.class);
            vResponseDto.setGenreName(ve.getGenre().getName());
            return vResponseDto;
        } catch (Exception e) {
            throw new NoSuchElementException("The Video with the given VIDEO ID is not present");
        }
    }

    public List<VideoResponseDto> getAllVideos() {
        List<VideoEntity> lVideoEntities = repo.findAll();
        return lVideoEntities.stream().map(ve -> {
            VideoResponseDto vResponseDto = mapper.map(ve, VideoResponseDto.class);
            vResponseDto.setGenreName(ve.getGenre().getName());
            return vResponseDto;
        }).toList();
    }

    public List<VideoResponseDto> getAllVideosfromGenre(int genreId) {
        List<VideoEntity> lVideoEntities = repo.findByGenreId(genreId);
        return lVideoEntities.stream().map(ve -> {
            VideoResponseDto vResponseDto = mapper.map(ve, VideoResponseDto.class);
            vResponseDto.setGenreName(ve.getGenre().getName());
            return vResponseDto;
        }).toList();
    }

    public VideoResponseDto updateVideo(VideoRequestDto inVideoRequestDto, int videoId) throws NoSuchElementException {
        VideoEntity ve;
        UserEntity userEntity;
        GenreEntity genreEntity;
        // System.out.println(inVideoRequestDto);

        try {
            ve = repo.findById(videoId).get();
        } catch (Exception e) {
            throw new NoSuchElementException("The Video with the given VIDEO ID is not present");
        }

        if (inVideoRequestDto.getUserId() != 0) {
            try {
                userEntity = userRepo.findById(inVideoRequestDto.getUserId()).get();
                ve.setUser(userEntity);
            } catch (Exception e) {
                throw new NoSuchElementException("The User With the given USER ID is not present");
            }
        }

        if (inVideoRequestDto.getGenreId() != 0) {
            try {
                genreEntity = genRepo.findById(inVideoRequestDto.getGenreId()).get();
                ve.setGenre(genreEntity);
            } catch (Exception e) {
                throw new NoSuchElementException("The Genre With the given GENRE ID is not present");
            }
        }

        ve.setTitle(inVideoRequestDto.getTitle() == null ? ve.getTitle() : inVideoRequestDto.getTitle());
        ve.setDescription(
                inVideoRequestDto.getDescription() == null ? ve.getDescription() : inVideoRequestDto.getDescription());
        ve.setUrl(inVideoRequestDto.getUrl() == null ? ve.getUrl() : inVideoRequestDto.getUrl());

        ve = repo.save(ve);
        VideoResponseDto videoResponseDto = mapper.map(ve, VideoResponseDto.class);
        videoResponseDto.setVideo(ve);
        return videoResponseDto;
    }

    public void deleteVideo(int videoId) throws NoSuchElementException {
        VideoEntity ve = repo.findById(videoId).get();

        repo.delete(ve);
    }
}
