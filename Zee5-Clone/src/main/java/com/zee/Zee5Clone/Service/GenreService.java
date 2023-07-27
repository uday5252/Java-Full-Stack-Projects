package com.zee.Zee5Clone.Service;

import com.zee.Zee5Clone.Entity.Genre;
import com.zee.Zee5Clone.Entity.Video;
import com.zee.Zee5Clone.Repository.GenreRepository;
import com.zee.Zee5Clone.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    VideoRepository videoRepository;

    @Autowired
    KafkaTemplate<Integer,String> kt;

    public List<Genre> getGenres(){
        List<Genre> genres =genreRepository.findAll();
        return genres;
    }
    public void addGenre(Genre genre){
        genreRepository.save(genre);
    }

    public Genre updateGenre(Genre genre, int genreId){
        Genre genreData = genreRepository.findById(genreId).get();
        String newDescription = genre.getDescription();
        String newName = genre.getName();
        if(newName!=null){
            genreData.setName(newName);
        }
        if(newDescription!=null){
            genreData.setDescription(newDescription);
        }
        genreRepository.save(genreData);
        Genre updatedGenreData = genreRepository.findById(genreId).get();

        kt.send("genre_uploads","genre has been updated");
        return updatedGenreData;
    }

    public void deleteGenre(int genreId){

        List<Video> videos = videoRepository.findBygenreId(genreId);
        for(Video video:videos){
            video.setGenreId(null);
        }
        videoRepository.saveAll(videos);
        genreRepository.deleteById(genreId);

    }
}
