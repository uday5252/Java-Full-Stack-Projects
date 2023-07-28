package com.example.demo.End.project.CONTROLLER;

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

import com.example.demo.End.project.ENTITY.GenreEntity;
import com.example.demo.End.project.SERVICE.GenreService;

@RestController
public class GenreController {
	
	   @Autowired
	   GenreService gs;
       @GetMapping("/api/genres")
       public ResponseEntity<List<GenreEntity>> getGenres()
       {
    	   return new ResponseEntity<>(gs.listOfGenres(),HttpStatus.OK);
       }
       
       @PostMapping("/api/admin/genre")
       public ResponseEntity<String> addGenre(@RequestBody GenreEntity ge)
       {
    	   gs.createGenre(ge);
    	   return new ResponseEntity<>("Genre created Successfully", HttpStatus.ACCEPTED);
       }
       @PutMapping("/api/admin/genres/{genreid}")
       public ResponseEntity<String> UpdateGenre(@PathVariable("genreid") int id, @RequestBody GenreEntity ge)
       {
    	   gs.UpdateGenre(ge,id);
		return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
    	   
       }
       @DeleteMapping("/api/admin/genres/{genreid}")
       public ResponseEntity<String> DeleteGenre(@PathVariable("genreid") int id)
       {
    	   gs.deleteGenre(id);
		return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    	   
       }
}
