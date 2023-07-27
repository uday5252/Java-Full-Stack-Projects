package com.abc.demo.Final.Project.implementing.all.CONTROLLER;

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

import com.abc.demo.Final.Project.implementing.all.Entity.GenreEntity;
import com.abc.demo.Final.Project.implementing.all.SERVICE.GenreService;

@RestController
public class GenreController {

	
	@Autowired
	GenreService gs;
	
	
	@PostMapping("/add/genre")
	public ResponseEntity<String> addGenre(@RequestBody GenreEntity ge){
		gs.Genreadd(ge);
		return new ResponseEntity<>("Genre added successfully",HttpStatus.CREATED);
	}
	
	@GetMapping("/get/genre")
	public ResponseEntity<List<String>> getGenre(){
		List<String> lg = gs.Genreget();
		return new ResponseEntity<>(lg,HttpStatus.OK);
	}
	
	@PutMapping("/edit/genre/{gid}")
	public ResponseEntity<String> editGenre(@RequestBody GenreEntity ge, @PathVariable("gid") int gid  ){
		gs.Genreupdate(ge, gid);
		return new ResponseEntity<>("Edited successfully",HttpStatus.OK);
	} 
	
	@DeleteMapping("/delete/genre/{gid}")
	public ResponseEntity<String> deleteGenre(@PathVariable("gid") int gid){
		gs.Genredelete(gid);
		return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
	}
}
