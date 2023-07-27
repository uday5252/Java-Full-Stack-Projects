package com.example.training.Project.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.example.training.Project.Entities.UserEntity;
import com.example.training.Project.Entities.VideoEntity;
import com.example.training.Project.Repositories.GenreRepo;
import com.example.training.Project.Repositories.UserRepo;
import com.example.training.Project.Repositories.VideoRepo;

import reactor.core.publisher.Mono;

class shortUser{
    private String username;
    private String email;

    public shortUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

class shortGenre{
    private String genre;

    public shortGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
}

class shortVideo{
    private String title;
    private String description;
    private String url;
    private String uploaded_at;
    private shortUser uploaded_by;
    private shortGenre genre;

    public shortVideo(String title, String description, String url, String uploaded_at, shortUser uploaded_by,
            shortGenre genre) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.uploaded_at = uploaded_at;
        this.uploaded_by = uploaded_by;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUploaded_at() {
        return uploaded_at;
    }

    public void setUploaded_at(String uploaded_at) {
        this.uploaded_at = uploaded_at;
    }

    public shortUser getUploaded_by() {
        return uploaded_by;
    }

    public void setUploaded_by(shortUser uploaded_by) {
        this.uploaded_by = uploaded_by;
    }

    public shortGenre getGenre() {
        return genre;
    }

    public void setGenre(shortGenre genre) {
        this.genre = genre;
    }
}

@Service
public class VideoService {
    @Autowired
    VideoRepo vRepo;

    @Autowired
    UserRepo uRepo;

    @Autowired
    GenreRepo gRepo;

    @Autowired
    ResourceLoader loader;

    public void createVideo(VideoEntity video,int uid){//,int gid){
        UserEntity user=uRepo.findById(uid).get();
        video.setUserId(user);
        // System.out.println(video.getGenreId());
        // GenreEntity genre=gRepo.findById(gid).get();
        // video.setGenreId(genre);
        vRepo.save(video);
    }

    public Object oneVideoDetails(int id){
        VideoEntity vid=vRepo.findById(id).get();
        shortUser user=new shortUser(vid.getUserId().getUsername(), vid.getUserId().getEmail());
        shortGenre genre=new shortGenre(vid.getGenreId().getName());
        shortVideo video=new shortVideo(vid.getTitle(), vid.getDescription(), vid.getUrl(), vid.getUploaded_at(), user, genre);
        return video;
    }

    public List<Object> allVideos(){
        List<VideoEntity>videos = vRepo.findAll();
        List<Object>allVideosDeets=new ArrayList<Object>();
        for(VideoEntity vid:videos){
            shortUser user=new shortUser(vid.getUserId().getUsername(), vid.getUserId().getEmail());
            shortGenre genre=new shortGenre(vid.getGenreId().getName());
            shortVideo video=new shortVideo(vid.getTitle(), vid.getDescription(), vid.getUrl(), vid.getUploaded_at(), user, genre);
            allVideosDeets.add(video);
        }
        return allVideosDeets;
    }

    public void updatevid(int id,VideoEntity newVideo){
        VideoEntity oldVideo=vRepo.findById(id).get();
        if(newVideo.getDescription()!=null)oldVideo.setDescription(newVideo.getDescription());
        if(newVideo.getTitle()!=null)oldVideo.setTitle(newVideo.getTitle());
        if(newVideo.getGenreId()!=null)oldVideo.setGenreId(newVideo.getGenreId());
        vRepo.save(oldVideo);
        // return oldVideo;
    }

    public void deleteOneVideo(int id){
        vRepo.deleteById(id);
    }

    public Mono<Resource> streamVideo(String name){
        return Mono.fromSupplier(()->{
            return loader.getResource("classpath:videos/"+name+".mp4");
        });
    }
}
