package com.abc.demo.Zee5.Apache.Kafka.Producer.CONTROLLER;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.GenreEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.PRODUCER.KafkaProducer;
import com.abc.demo.Zee5.Apache.Kafka.Producer.SERVICE.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    GenreService genreService;

    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping("/genre/add")
    public ResponseEntity<String> addGenre(@RequestBody GenreEntity genre){
        genreService.addGenre(genre);
        return new ResponseEntity<String>("Genre added", HttpStatus.CREATED);
    }

    @GetMapping("/genre/list")
    public ResponseEntity<List<GenreEntity>> listGenre(){
        List<GenreEntity> genre = genreService.getAllGenres();
        return new ResponseEntity<List<GenreEntity>>(genre, HttpStatus.OK);
    }

    @PutMapping("/genre/{genre_id}/update")
    public void updateGenre(@RequestBody GenreEntity genreData, @PathVariable("genre_id") int genre_id){
        kafkaProducer.sendDataOfGenreUpdates("Genre has been updated");
        genreService.updateGenre(genreData, genre_id);
    }

    @DeleteMapping("/genre/{genre_id}/delete")
    public void deleteGenre(@PathVariable("genre_id") int genre_id){
        genreService.deleteGenreById(genre_id);
    }
}
