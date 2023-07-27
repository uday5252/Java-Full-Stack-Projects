package com.zee.Zee5Clone.Controller;

import com.zee.Zee5Clone.Entity.Genre;
import com.zee.Zee5Clone.Service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {
    @Autowired
    GenreService genreService;

    @GetMapping("/genres")
    public ResponseEntity<List<Genre>> getGenre(){
        List<Genre> genres =genreService.getGenres();
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @PostMapping("/admin/genres")
    public ResponseEntity<String> addGenre(@RequestBody Genre genre){
        genreService.addGenre(genre);
        return new ResponseEntity<>("added Successfully",HttpStatus.OK);
    }

    @PutMapping("/admin/genres/{genreId}")
    public ResponseEntity<Genre> updateGenre(@RequestBody Genre genre,@PathVariable("genreId") int genreId){
        Genre updaedGenre =genreService.updateGenre(genre,genreId);
        return new ResponseEntity<>(updaedGenre,HttpStatus.OK);
    }

    @DeleteMapping("/admin/genres/{genreId}")
    public ResponseEntity<List<Genre>> deleteGenre(@PathVariable("genreId") int genreId){
        genreService.deleteGenre(genreId);
        List<Genre> genres = genreService.getGenres();
        return new ResponseEntity<>(genres,HttpStatus.OK);
    }

}
