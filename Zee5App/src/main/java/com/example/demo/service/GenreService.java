package com.example.demo.service;


import com.example.demo.entity.Genre;
import com.example.demo.kafka.KafkaProducer;
import com.example.demo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepository gri;

    @Autowired
    KafkaProducer kp;
    public void collectGenreDetails(Genre genre) {
        gri.save(genre);
        kp.sendToTopic("genre_updates","Genre "+genre.getGid()+" is updated Succeessfully");
    }


    public List<Genre> getAllGenre() {
        return gri.findAll();
    }

    public void deleteGenreById(int genreId) {
        gri.deleteById(genreId);
    }
}
