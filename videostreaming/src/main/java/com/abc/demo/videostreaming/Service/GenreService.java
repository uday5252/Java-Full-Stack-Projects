package com.abc.demo.videostreaming.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.videostreaming.Entity.Genre;

@Service
public class GenreService {
    @Autowired
    KafkaTemplate<String,String> kt;

    public void notifyGenreUpdate(Genre tempGenre) {
        kt.send("genre_updates",tempGenre.toString());
    }
}
