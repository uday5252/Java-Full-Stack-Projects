package com.videostreamingapp.com.Assignment.project.CONTROLLER;

import com.videostreamingapp.com.Assignment.project.ENTITY.GenreEntity;
import com.videostreamingapp.com.Assignment.project.ENTITY.UserEntity;
import com.videostreamingapp.com.Assignment.project.ENTITY.VideoEntity;
import com.videostreamingapp.com.Assignment.project.PRODUCER.KafkaProducer;
import com.videostreamingapp.com.Assignment.project.SERVICE.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {
    @Autowired
    GenreService gs;
    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping("add/genre")
    public ResponseEntity<String> postCreate(@RequestBody GenreEntity ge){
        gs.genreCreate(ge);
        return new ResponseEntity<>("Genre has been created", HttpStatus.CREATED);
    }
    @GetMapping("show/genre")
    public ResponseEntity<List<String>>allGenre(){
        List<String> zonara = gs.readAllGenre();
        return new ResponseEntity<>(zonara,HttpStatus.OK);
    }
    @PutMapping("show/genre/{genre_id}/update")
    public ResponseEntity<GenreEntity>updateGenre(@PathVariable GenreEntity genre,@PathVariable int genre_id){
        GenreEntity updated = gs.updateGenre(genre,genre_id);


        kafkaProducer.sendDataofGenreUploads("Genre has been updated");
        return new ResponseEntity<GenreEntity>(updated,HttpStatus.OK);

    }
    @DeleteMapping("genre/{genre_id}/delete")
    public ResponseEntity<String> deleteGenre(@PathVariable int genre_id){
        gs.deleteGenre(genre_id);
        return new ResponseEntity<String>("Genre has been deleted",HttpStatus.OK);

    }

}
