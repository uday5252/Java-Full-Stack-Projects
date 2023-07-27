package com.sivasai.endGame.SpringFinalProject.Service;

import com.sivasai.endGame.SpringFinalProject.Entity.Genre;
import com.sivasai.endGame.SpringFinalProject.Entity.User;
import com.sivasai.endGame.SpringFinalProject.Entity.Video;
import com.sivasai.endGame.SpringFinalProject.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class VideoService {

    @Autowired
    VideoRepository videoRepository;

    public Video saveVideo(Video video){
//        System.out.println("reached here");
        return videoRepository.save(video);
    }

    public List<Video> getAllVideos(){
        return videoRepository.findAll();
    }

    public Boolean ifExists(int videoId){
        return videoRepository.existsById(videoId);
    }

    public Video getVideoById(int videoId){
        return videoRepository.findById(videoId).get();
    }

    public Video getVideoByName(String videoName) {
        return videoRepository.findByTitle(videoName).get();
    }

    public Boolean ifExistsByTitle(String title){
        return videoRepository.existsByTitle(title);
    }

    public Video updateVideo(Video video, int videoId) throws IllegalAccessException, NoSuchFieldException {
        Video existingVideo = videoRepository.findById(videoId).get();
        for(Field field: video.getClass().getDeclaredFields()){
            String fieldName = field.getName();
            if(fieldName != "id" && fieldName != "uploaded_by"){
                Field f = existingVideo.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                if(field.get(video) != null){
//                    System.out.println(fieldName);
                    f.setAccessible(true);
                    f.set(existingVideo, field.get(video));
                }
            }
        }
//        System.out.println("coming here");
        return videoRepository.save(existingVideo);
    }

    public void deleteById(int videoId){
        videoRepository.deleteById(videoId);
    }

    public void deleteGenre(int genreId){
        List<Video> videoList = getAllVideos();
        List<Integer> ids = new ArrayList<>();
        for(Video video: videoList){
            Set<Genre> genreList = video.getGenre_id();
            for(Genre g : genreList){
                if(g.getId() == genreId){
                    video.getGenre_id().remove(g);
                    if(video.getGenre_id().isEmpty()){
                        ids.add(video.getId());
                    }
                    else {
                        videoRepository.save(video);
                        break;
                    }
                }
            }
        }
        if(!ids.isEmpty()) {
            for (int id : ids) {
                deleteById(id);
            }
        }
    }

    public List<Video> getByGenre(int genreId) {
        List<Video> videoList = getAllVideos();
        List<Video> returnList = new ArrayList<>();
        for (Video video : videoList) {
            Set<Genre> genreList = video.getGenre_id();
            for (Genre g : genreList) {
                if (g.getId() == genreId) {
                    returnList.add(video);
                }
            }
        }
        return returnList;
    }

    public void deleteByUser(User user){
        videoRepository.deleteByUploadedBy(user);
    }

    public Boolean checkExistsByUser(User user){
        return videoRepository.existsByUploadedBy(user);
    }
}
