package com.sam.demo.streaming.app.zee5.SERVICE;

import com.sam.demo.streaming.app.zee5.ENTITY.GenreEntity;
import com.sam.demo.streaming.app.zee5.ENTITY.UserEntity;
import com.sam.demo.streaming.app.zee5.ENTITY.VideoEntity;
import com.sam.demo.streaming.app.zee5.REPOSITORY.GenreRepository;
import com.sam.demo.streaming.app.zee5.REPOSITORY.UserRepository;
import com.sam.demo.streaming.app.zee5.REPOSITORY.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    @Autowired
    VideoRepository vr;
    @Autowired
    UserRepository ur;

    @Autowired
    GenreRepository gr;


    public VideoEntity uploadvideo(VideoEntity collectVideoData, int gid, int uid){
        GenreEntity ge=gr.findById(gid).get();
        collectVideoData.setGenre(ge);

        UserEntity ue=ur.findById(uid).get();
        collectVideoData.setUploaded_by(ue);

        vr.save(collectVideoData);
        return collectVideoData;
    }

    public VideoEntity  getVideoById(int vid){
        VideoEntity ve=vr.findById(vid).get();
        return ve;
    }

    public List<VideoEntity> getAllVideo(){
        List<VideoEntity> ve=vr.findAll();
        return ve;
    }
    public List<VideoEntity> getVideoByGenre(int gid){
        GenreEntity ge=gr.findById(gid).get();
        List<VideoEntity>  ve=vr.findALlByGenre(ge);
        return ve;
    }
    public void updateGenreById(int id, GenreEntity ge)
    {

        GenreEntity  gedata =gr.findById(id).get();
        gedata.setName(ge.getName()!= null? ge.getName():gedata.getName());
        gedata.setDescription(ge.getDescription()!= null?ge.getDescription(): gedata.getDescription());
        gr.save(gedata);
    }

    public void updateVideoDetails(VideoEntity ve,int vid){
        VideoEntity vedata=vr.findById(vid).get();
        vedata.setTitle(ve.getTitle()!= null? ve.getTitle():vedata.getTitle());
        vedata.setDescription(ve.getDescription()!=null?ve.getDescription():vedata.getDescription());
        vedata.setUrl(ve.getUrl()!=null?ve.getUrl():vedata.getUrl());
        vedata.setGenre(ve.getGenre()!=null?vedata.getGenre():vedata.getGenre());

        vr.save(vedata);
    }


    public void deleteVideoById(int id){
        VideoEntity ve=vr.findById(id).get();
        vr.delete(ve);
    }

    public String  getUrl(int vid){
        VideoEntity ve= vr.findById(vid).get();
        String url = ve.getUrl();
        return url;

    }



}
