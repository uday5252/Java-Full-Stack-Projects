package com.project.demo.end_to_end.controllers;

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

import com.project.demo.end_to_end.KafkaProducer;
import com.project.demo.end_to_end.entities.Genre;
import com.project.demo.end_to_end.entities.User;
import com.project.demo.end_to_end.service.GenreService;
import com.project.demo.end_to_end.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Genre Contoller", description = "This has endpoints to manipulate with genres")
@RestController
public class GenreController {
    @Autowired
    GenreService gs;
    @Autowired
    UserService us;
    @Autowired
    KafkaProducer kp;

    @GetMapping("/api/genres")
    public ResponseEntity<List<Genre>> getAllGenres(){
        return new ResponseEntity<>(gs.getAllGenres(),HttpStatus.OK);
    }
    @PostMapping("/api/admin/genres")
    public ResponseEntity<?> addGenre(@RequestBody Genre g,String username, String password){
        User user = us.findbycredentials(username, password);
        if(user == null)    return new ResponseEntity<String>("invalidCredentials",HttpStatus.NOT_ACCEPTABLE);
        gs.addGenre(g);
        return new ResponseEntity<Genre>(g,HttpStatus.OK);
    }
    @PutMapping("/api/admin/genres/{genreId}")
    public ResponseEntity<?> updateGenre(@PathVariable("genreId") int id,@RequestBody Genre g, String username, String password){
        User user = us.findbycredentials(username, password);
        if(user == null)    return new ResponseEntity<String>("invalidCredentials",HttpStatus.NOT_ACCEPTABLE);
        Genre gold = gs.getById(id);
        if(g.getName() != null) {
            kp.send("genre_updates", gold.getName()+" updated to "+g.getName());
            gold.setName(g.getName());
        }
        if(g.getDescription() != null) {
            kp.send("genre_updates","Description of "+gold.getName()+" is updated to "+g.getDescription());
            gold.setDescription(g.getDescription());
        }
        gs.save(gold);
        return new ResponseEntity<>(gold,HttpStatus.OK);
    }
    @DeleteMapping("/api/admin/geners/{genreId}")
    public ResponseEntity<String> deleteGenre(@PathVariable("genreId") int id, String username, String password){
        User user = us.findbycredentials(username, password);
        if(user == null)    return new ResponseEntity<String>("invalidCredentials",HttpStatus.NOT_ACCEPTABLE);
        gs.deleteGenre(id);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }
}
