package com.abc.demo.VIDEO.STREAMING.PLATFORM.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE.genreService;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.genreEntity;

@Controller
public class genreController {

	@Autowired
	genreService gs;
	
	@GetMapping("/Genres")
	public ResponseEntity<List<genreEntity>> getAllGenres() {
		
		List<genreEntity> genres=gs.getGenres();
		
		return new ResponseEntity<>(genres,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/addGenre")
	public ResponseEntity<String> addGenres(@RequestBody genreEntity ge) {
		
		gs.addGenre(ge);
		
	return new ResponseEntity<>("Genre added successfully",HttpStatus.CREATED);
	}
	
	
	@GetMapping("/genres/{genreId}")
	public ResponseEntity<genreEntity> GenreById(@PathVariable("genreId") int id) {
		
		genreEntity genre=gs.getGenreById(id);
		
		
	return new ResponseEntity<>(genre,HttpStatus.CREATED);	
			
	}
	
	@DeleteMapping("/DeleteGenre/{genreId}")
	public ResponseEntity<String> deleteGenre(@PathVariable("genreId") int id) {
		
		gs.deleteGenreById(id);
		
	return new ResponseEntity<>("Genre deleted successfully",HttpStatus.CREATED);	
	}
	
}
