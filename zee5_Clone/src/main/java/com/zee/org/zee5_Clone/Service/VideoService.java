package com.zee.org.zee5_Clone.Service;

import com.zee.org.zee5_Clone.Entity.GenreTable;
import com.zee.org.zee5_Clone.Entity.UserTable;
import com.zee.org.zee5_Clone.Entity.VideoTable;
import com.zee.org.zee5_Clone.Repository.GenreRepository;
import com.zee.org.zee5_Clone.Repository.UserRepository;
import com.zee.org.zee5_Clone.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class VideoService
{
    @Autowired
    VideoRepository Vr;
    @Autowired
    UserRepository Ur;
    @Autowired
    GenreRepository Gr;



    public void uploadVideo(VideoTable video){

//        System.out.println(Gr.findById(video.getGenre().getGenreid()));

        GenreTable data =Gr.findById(video.getGenre().getGenreid()).get();
        UserTable data2=Ur.findById(video.getUser().getUserid()).get();
        if(data != null && data2 != null){
            Vr.save(video);
        }
        else
            System.out.println("no user or genre for provided id");
    }

    public VideoTable viewVideo(int a){
        return Vr.findById(a).get();
    }

    public List<VideoTable> viewAllVideos(){
        return Vr.findAll();
    }

    public List<VideoTable> viewVideoByGenreId(int a){
        GenreTable genre=Gr.findById(a).get();

        List<VideoTable> lis= Vr.findByGenre(genre);
        return lis;
    }
    public void updateVideo(int a,VideoTable vid){
        VideoTable v=Vr.findById(a).get();
        GenreTable g=Gr.findById(vid.getGenre().getGenreid()).get();
        UserTable u=Ur.findById(vid.getUser().getUserid()).get();

        v.setDiscription(vid.getDiscription());
        v.setGenre(vid.getGenre());
        v.setUrl(vid.getUrl());
        v.setTitle(vid.getTitle());
        v.setUser(vid.getUser());

        Vr.save(v);
    }

    public void delVideoWithVideoId(int a){
        Vr.deleteById(a);
    }



}
