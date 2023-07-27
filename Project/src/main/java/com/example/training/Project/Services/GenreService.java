package com.example.training.Project.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.training.Project.Entities.GenreEntity;
import com.example.training.Project.Entities.VideoEntity;
import com.example.training.Project.Repositories.GenreRepo;

@Service
public class GenreService {
    
    @Autowired
    GenreRepo gRepo;

    public void createGenre(GenreEntity genre){
        System.out.println(genre.getName());
        gRepo.save(genre);
    }

    public List<GenreEntity> getGenres(){
        return gRepo.findAll();
    }

    public void updateGenre(GenreEntity newGenre,int id){
        GenreEntity old=gRepo.findById(id).get();
        old.setDescription(newGenre.getDescription());
        old.setName(newGenre.getName());
        gRepo.save(old);
    }

    public void deleteGenre(int id){
        gRepo.deleteById(id);
    }

    public List<Object> getVids(int id){
        List<VideoEntity> videos= gRepo.findById(id).get().getVideos();
        List<Object>allVideosDeets=new ArrayList<Object>();
        for(VideoEntity vid:videos){
            shortUser user=new shortUser(vid.getUserId().getUsername(), vid.getUserId().getEmail());
            shortGenre genre=new shortGenre(vid.getGenreId().getName());
            shortVideo video=new shortVideo(vid.getTitle(), vid.getDescription(), vid.getUrl(), vid.getUploaded_at(), user, genre);
            allVideosDeets.add(video);
        }
        return allVideosDeets;
    }
}
