package com.example.training.Project.Controllers;

import java.util.ArrayList;
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
import org.springframework.web.client.RestTemplate;

import com.example.training.Project.Entities.GenreEntity;
import com.example.training.Project.Services.GenreService;

class response{
    private int id;
    private String genre;

    public response(int id, String genre){
        this.id = id;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
}

@RestController
public class GenreController {
    
    @Autowired
    GenreService gService;

    RestTemplate restTemplate;

    @PostMapping("/api/admin/genres")
    public ResponseEntity<String> createGenre(@RequestBody GenreEntity genre){
        gService.createGenre(genre);
        return new ResponseEntity<>("genre created successfully!",HttpStatus.OK);
    }

    @GetMapping("/api/genres")
    public ResponseEntity<List<response>>getGenres(){
        List<GenreEntity> genres=gService.getGenres();
        List<response> responses=new ArrayList<>();
        for(GenreEntity g:genres){
            System.out.println(g.getVideos());
            response r=new response(g.getId(), g.getName());
            responses.add(r);
        }
        // ResponseEntity<List<GenreEntity>>res=restTemplate.getForEntity(null, null, null)
        return new ResponseEntity<List<response>>(responses,HttpStatus.OK);
    }

    @PutMapping("/api/admin/genres/{id}")
    public ResponseEntity<String> updateGenre(@RequestBody GenreEntity genre,@PathVariable("id") int id){
        gService.updateGenre(genre,id);
        return new ResponseEntity<>("successfully updated",HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/genres/{id}")
    public ResponseEntity<String>deleteGenre(@PathVariable("id") int id){
        gService.deleteGenre(id);
        return new ResponseEntity<String>("Genre Deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/api/genres/{id}/videos")
    public ResponseEntity<List<Object>>getGenreVideos(@PathVariable("id") int id){
        return new ResponseEntity<List<Object>>(gService.getVids(id), HttpStatus.OK);
    }
}
