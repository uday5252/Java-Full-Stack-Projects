package com.abc.project1.service;

import com.abc.project1.ExceptionHandling.CustomException.InvalidIdException;
import com.abc.project1.ExceptionHandling.CustomException.ResourceNotFoundException;
import com.abc.project1.entity.Genre;
import com.abc.project1.entity.User;
import com.abc.project1.entity.Video;
import com.abc.project1.repository.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VideoService {
    @Autowired
    VideoRepo vr;
    @Autowired
    UserService us;
    @Autowired
    GenreService gs;
    @Autowired
    ResourceLoader loader;

    public Video addThisVideo(Video video, int uploadedById, Set<Integer> genresIds) throws InvalidIdException, ResourceNotFoundException {
        if(uploadedById <= 0){
            throw new InvalidIdException(uploadedById);
        }

        // set uploadedBy User
        User user = us.getThisByUserId(uploadedById);
        if(user == null){
            throw new ResourceNotFoundException(uploadedById);
        }
        video.setUploadedBy(user);

        // add all genres
        Set<Genre> genres = new HashSet<>();
        for (int genreId: genresIds) {
            if(genreId <= 0){
                throw new InvalidIdException(genreId);
            }

            Genre genre = gs.getByGenreId(genreId);
            if(genre == null){
                throw new ResourceNotFoundException(genreId);
            }
            genres.add(genre);
        }

        video.setGenres(genres);

        return vr.save(video);
    }

    public List<Video> getAllVideos() {
        return vr.findAll();
    }

    public Video getThisVideo(int videoId) throws InvalidIdException, ResourceNotFoundException {
        if(videoId <= 0){
            throw new InvalidIdException(videoId);
        }
        Video video = vr.findByVid(videoId);
        if(video == null){
            throw new ResourceNotFoundException(videoId);
        }
        return video;
    }

    public Video updateThisVideo(int videoId, Video details) throws InvalidIdException, ResourceNotFoundException {
        if(videoId <= 0){
            throw new InvalidIdException(videoId);
        }
        Video videoToUpdate = vr.findByVid(videoId);
        if(videoToUpdate == null){
            throw new ResourceNotFoundException(videoId);
        }
        if(details.getVideoTitle() != null && details.getVideoTitle().length() != 0){
            videoToUpdate.setVideoTitle(details.getVideoTitle());
        }
        if(details.getVideoDescription() != null && details.getVideoDescription().length() != 0){
            videoToUpdate.setVideoDescription(details.getVideoDescription());
        }
        if(details.getVideoUrl() != null && details.getVideoUrl().length() != 0){
            videoToUpdate.setVideoUrl(details.getVideoUrl());
        }
        return videoToUpdate;
    }

    public void deleteThisVideo(int videoId) throws InvalidIdException, ResourceNotFoundException {
        if(videoId <= 0){
            throw new InvalidIdException(videoId);
        }
        Video video = vr.findByVid(videoId);
        if(video == null){
            throw new ResourceNotFoundException(videoId);
        }
        vr.delete(video);
    }

    public void updateGenresOnThisVideo(int videoId, Set<Integer> genresIds) throws InvalidIdException, ResourceNotFoundException {
        if(videoId <= 0){
            throw new InvalidIdException(videoId);
        }
        Video video = vr.findByVid(videoId);
        if(video == null){
            throw new ResourceNotFoundException(videoId);
        }
        Set<Genre> genres = new HashSet<>();
        for (int genreId: genresIds) {
            if(genreId <= 0){
                throw new InvalidIdException(genreId);
            }
            Genre genre = gs.getByGenreId(genreId);
            if(genre == null){
                throw new ResourceNotFoundException(genreId);
            }
            genres.add(genre);
        }

        video.setGenres(genres);
        vr.save(video);
    }

    public Mono<Resource> readVideo(int videoId) throws InvalidIdException, ResourceNotFoundException {
        if(videoId <= 0){
            throw new InvalidIdException(videoId);
        }
        Video video = vr.findByVid(videoId);
        if(video == null){
            throw new ResourceNotFoundException(videoId);
        }
        return Mono.fromSupplier(() -> loader.getResource(video.getVideoUrl()));
    }

}
