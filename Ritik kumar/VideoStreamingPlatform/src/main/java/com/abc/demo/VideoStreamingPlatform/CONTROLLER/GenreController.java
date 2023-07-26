package com.abc.demo.VideoStreamingPlatform.CONTROLLER;

import com.abc.demo.VideoStreamingPlatform.ENTITY.GenreEntity;
import com.abc.demo.VideoStreamingPlatform.SERVICE.GenreService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    GenreService gs;

    @PostMapping("/api/admin/genres")
    public ResponseEntity<String> addNewGenre(@RequestBody GenreEntity genre)
    {
        System.out.println(genre);
        gs.createGenre(genre);
        return new ResponseEntity<>("New Genre Added", HttpStatus.CREATED);
    }

    @GetMapping("/api/genres")
    public ResponseEntity<List<GenreEntity>> allGenre()
    {
        return new ResponseEntity<>(gs.showAllGenre(),HttpStatus.OK);
    }

    @PutMapping("/api/admin/genres/{genreId}")
    public ResponseEntity<String> updateGenreDetails(@RequestBody GenreEntity genre,@PathVariable("genreId") int gId)
    {
        gs.updateGenre(genre,gId);
        return new ResponseEntity<>("Genre is Updated",HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/genres/{genreId}")
    public ResponseEntity<String> deleteGenreById(@PathVariable("genreId") int gId)
    {
        gs.deleteGenre(gId);
        return new ResponseEntity<>("Genre Deleted Successfully",HttpStatus.OK);
    }
}
