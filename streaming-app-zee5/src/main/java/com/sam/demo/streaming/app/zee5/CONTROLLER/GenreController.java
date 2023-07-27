package com.sam.demo.streaming.app.zee5.CONTROLLER;

import com.sam.demo.streaming.app.zee5.ENTITY.GenreEntity;
import com.sam.demo.streaming.app.zee5.ENTITY.UserEntity;
import com.sam.demo.streaming.app.zee5.PRODUCER.KafkaProducer;
import com.sam.demo.streaming.app.zee5.SERVICE.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GenreController {
    @Autowired
    GenreService gs;
    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping("/api/admin/genres")
    @ResponseStatus(HttpStatus.CREATED)
    public GenreEntity addGenre(@RequestBody GenreEntity ge){
        gs.addGenre(ge);
        kafkaProducer.sendDataofGenreUploads("Genre has been updated");
        return ge;

    }



    @GetMapping("/api/genres")
   public ResponseEntity<List<GenreEntity>> collectALlGenres(){
        List<GenreEntity> list=gs.getGenre();
        return  new ResponseEntity<>(list,HttpStatus.OK);
   }
//    @PutMapping("show/genre/{genre_id}/update")
//    public ResponseEntity<GenreEntity>updateGenre(@PathVariable GenreEntity genre,@PathVariable int genre_id){
//        GenreEntity updated = gs.updateGenre(genre,genre_id);
//
//
//        kafkaProducer.sendDataofGenreUploads("Genre has been updated");
//        return new ResponseEntity<GenreEntity>(updated,HttpStatus.OK);
//
//    }
    @PutMapping("/api/admin/genres/{genreid}")
    public ResponseEntity<String> updateGenre(@RequestBody  GenreEntity ge, @PathVariable ("genreid") int id)
    {
        gs.updateGenreById(id,ge);

        kafkaProducer.sendDataofGenreUploads("Genre has been updated");

        return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
    }
    @DeleteMapping("/api/admin/genres/{genreId}")
    public ResponseEntity<String> deleteGenreById(@PathVariable ("genreId") int id)
    {
        gs.deleteGenreById(id);

        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }


}
