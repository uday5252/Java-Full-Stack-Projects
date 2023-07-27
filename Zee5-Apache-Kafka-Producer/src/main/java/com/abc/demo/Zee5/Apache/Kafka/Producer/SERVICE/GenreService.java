package com.abc.demo.Zee5.Apache.Kafka.Producer.SERVICE;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.GenreEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public void addGenre(GenreEntity genreData){
        genreRepository.save(genreData);
    }

    public List<GenreEntity> getAllGenres(){
        List<GenreEntity> genreList = genreRepository.findAll();
        return genreList;
    }

    public void updateGenre(GenreEntity genre, int genre_id){
        GenreEntity genreEntity = genreRepository.findById(genre_id).get();
        genreEntity.setGenre_name(genre.getGenre_name() != null ? genre.getGenre_name() : genreEntity.getGenre_name());
        genreEntity.setGenre_description(genre.getGenre_description() != null ? genre.getGenre_description() : genreEntity.getGenre_description());

        genreRepository.save(genreEntity);
    }

    public void deleteGenreById(int genre_id){
        GenreEntity genreEntity = genreRepository.findById(genre_id).get();
        genreRepository.deleteById(genreEntity.getGenre_id());
    }
}
