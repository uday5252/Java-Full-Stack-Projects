package com.abc.demo.videostreaming.Controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.videostreaming.Entity.Genre;
import com.abc.demo.videostreaming.EntityRepository.GenreInterfaceRepository;
import com.abc.demo.videostreaming.Service.GenreService;
import com.abc.demo.videostreaming.Service.VideoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(
    name = "Genre Controller",
    description = "Genre Controller"
)
@RestController
public class GenreController {

    @Autowired
    GenreInterfaceRepository gir;

    @Autowired
    VideoService vs;

    @Autowired
    GenreService gs;

    @Operation(
        description = "Get All Genres",
        summary = "Get All Genres"
    )
    @GetMapping("/api/genres")
    List<Genre> getGenres(){
        return gir.findAll();
    }

    @Operation(
        description = "Add Genre",
        summary = "Add Genre"
    )
    @PostMapping("/api/admin/genres")
    List<Genre> addGenre(@RequestBody Genre genre){
        gir.save(genre);
        return gir.findAll();
    }

    @Operation(
        description = "Upadate Genre",
        summary = "Add Genre"
    )
    @PutMapping("/api/admin/genres/{genreId}")
    ResponseEntity<String> updateGenre(@RequestBody Genre genre,@PathVariable("genreId") int id){
        Genre tempGenre = gir.getReferenceById(id);
        if(genre.getName()!=null){
            tempGenre.setName(genre.getName());
        }
        if(genre.getDescription()!=null){
            tempGenre.setDescription(genre.getDescription());
        }
        try {
            gir.save(tempGenre);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        gs.notifyGenreUpdate(tempGenre);
        //return gir.findAll();
        return new ResponseEntity<String>("Updated", HttpStatus.OK);
    }

    @Operation(
        description = "Delete Genre",
        summary =  "Delete Genre"
    )
    @DeleteMapping("/api/admin/genres/{genreId}")
    ResponseEntity<String> deleteVideo(@PathVariable("genreId") int id){
        try {
            gir.delete(gir.getReferenceById(id));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<String>("Delete", HttpStatus.OK);
    }
}
