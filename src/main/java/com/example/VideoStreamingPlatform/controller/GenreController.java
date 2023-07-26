package com.example.VideoStreamingPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.VideoStreamingPlatform.entity.Genre;
import com.example.VideoStreamingPlatform.producer.Producer;
import com.example.VideoStreamingPlatform.service.GenreService;

@RestController
@RequestMapping("/api")
public class GenreController {
  @Autowired
  GenreService gs;
  @Autowired
  Producer p;

  @PostMapping("/genre/create")
  public ResponseEntity<Genre> createGenre(@RequestBody Genre newGenre) {
    Genre createdGenre = gs.createGenre(newGenre);
    return new ResponseEntity<Genre>(createdGenre, HttpStatus.CREATED);
  }

  @GetMapping("/genres")
  public ResponseEntity<List<Genre>> getAllGenres() {
    List<Genre> allGenres = gs.getAllGenres();
    return new ResponseEntity<List<Genre>>(allGenres, HttpStatus.OK);
  }

  @PutMapping("/genres/{genre_id}/update")
  public ResponseEntity<Genre> updateGenre(@PathVariable int genre_id, @RequestBody Genre genre) {
    Genre updatedGenre = gs.updateGenre(genre_id, genre);
    p.sendGenreUpdateDataToTopic("Genre " + genre_id + " has just been updated.");
    return new ResponseEntity<Genre>(updatedGenre, HttpStatus.OK);
  }

  @DeleteMapping("genres/{genre_id}/delete")
  public ResponseEntity<String> deleteGenre(@PathVariable int genre_id) {
    gs.deleteGenre(genre_id);
    return new ResponseEntity<String>("Genre deleted.", HttpStatus.OK);
  }
}
