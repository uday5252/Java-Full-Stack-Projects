package com.videostreamingapp.com.Assignment.project.SERVICE;

import com.videostreamingapp.com.Assignment.project.ENTITY.GenreEntity;
import com.videostreamingapp.com.Assignment.project.ENTITY.UserEntity;
import com.videostreamingapp.com.Assignment.project.ENTITY.VideoEntity;
import com.videostreamingapp.com.Assignment.project.REPOSITORY.GenreRepository;
import com.videostreamingapp.com.Assignment.project.REPOSITORY.UserRepository;
import com.videostreamingapp.com.Assignment.project.REPOSITORY.VideoRepository;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    VideoRepository vr;
    @Autowired
    UserRepository ur;
    @Autowired
    GenreRepository gr;

    public VideoEntity uploadVideo(int user_id, int genre_id, VideoEntity video){
        UserEntity uploader = ur.findById(user_id).get();
        GenreEntity genre = gr.findById(genre_id).get();
        video.setUploaded_by(uploader);
        video.setGenre_id(genre);
        vr.save(video);
        return vr.findById(video.getVideo_id()).get();
    }
    public VideoEntity getVideo(int video_id ){
       return  vr.findById(video_id).get();
    }
    public List<VideoEntity> allVideo(){
        return vr.findAll();

    }
    public List<VideoEntity> getVideoByGenre(int genre_id){
        GenreEntity genre = gr.findById(genre_id).get();
        List<VideoEntity> list = vr.findAllByGenre(genre);
        return list;

    }

    public void updateVideoDetails(VideoEntity video,int video_id){
        VideoEntity vd =vr.findById(video_id).get();

        vd.setTitle(video.getTitle() != null ? video.getTitle():vd.getTitle());
        vd.setDescription(video.getDescription()!=null?video.getDescription(): vd.getDescription());
        vd.setUrl(video.getUrl()!=null?video.getUrl(): vd.getUrl());
        vd.setGenre_id(video.getGenre_id()!=null ? video.getGenre_id():vd.getGenre_id());

        vr.save(vd);

    }
    public void deleteVideo(int video_id){
        VideoEntity ve = vr.findById(video_id).get();
        vr.delete(ve);
    }
    public String get_url(int video_id){
        VideoEntity video= vr.findById(video_id).get();
        String url = video.getUrl();
        return url;

    }


}
