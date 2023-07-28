package com.example.demo.controller;


import com.example.demo.entity.Genre;
import com.example.demo.service.GenreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Genre Controller", description = "This controller controls all the endpoints associated with the GENRE functions related to the application")


@RestController
public class GenreController {

    @Autowired
    GenreService genreService;

    @PostMapping("/api/admin/genres")
    public ResponseEntity<String> createGenre(@RequestBody Genre genre){
        genreService.collectGenreDetails(genre);

        return ResponseEntity.ok().body("Created Genre Successfully");

    }


    @GetMapping("/api/genres")
    public ResponseEntity<List<Genre>> getGenres(){
        List<Genre> g=genreService.getAllGenre();
        return ResponseEntity.ok().body(g);
    }

    @PutMapping("/api/admin/genres/{genreId}")
    public ResponseEntity<String> updateGenre(@PathVariable int genreId,@RequestBody Genre genre){
        genre.setGid(genreId);
        genreService.collectGenreDetails(genre);

        return ResponseEntity.ok().body("Updated Genre Successfully");

    }

    @DeleteMapping("/api/admin/genres/{genreId}")
    public ResponseEntity<String> deleteGenre(@PathVariable int genreId){
         genreService.deleteGenreById(genreId);
        return ResponseEntity.ok().body("Deleted Genre Successfully");
    }







}
