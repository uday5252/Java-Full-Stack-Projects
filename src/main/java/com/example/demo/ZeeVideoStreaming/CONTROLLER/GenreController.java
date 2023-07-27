package com.example.demo.ZeeVideoStreaming.CONTROLLER;

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

import com.example.demo.ZeeVideoStreaming.ENTITY.GenreEntity;
import com.example.demo.ZeeVideoStreaming.SERVICE.GenreService;

@RestController
public class GenreController {

	@Autowired
	GenreService gs;

	@PostMapping("/api/admin/genres")
	public ResponseEntity<Object> createGenre(@RequestBody GenreEntity g) {

		GenreEntity g1 = gs.createGenreInDB(g);
		return ResponseHandler.generateResponse("genre created", HttpStatus.CREATED, g1, true);
	}

	@GetMapping("/api/genres")
	public ResponseEntity<Object> getAllGenre() {
		return ResponseHandler.generateResponse("retrived all genres from the database", HttpStatus.ACCEPTED,
				gs.getAllGenresFromDB(), true);
	}

	@PutMapping("/api/admin/genres/{genreId}")
	public ResponseEntity<Object> updateGenreById(@PathVariable("genreId") long id, @RequestBody GenreEntity g) {
		GenreEntity g1 = gs.updateExistingGenreInDB(id, g);
		return ResponseHandler.generateResponse("genre updated successfully", HttpStatus.OK, g1, true);
	}

	@DeleteMapping("/api/admin/genres/{genreId}/")
	public ResponseEntity<Object> deleteGenreById(@PathVariable("genreId") long id) {
		boolean status = gs.deleteGenreInDB(id);
		if(status)
			return ResponseHandler.generateResponse("genre deleted successully", HttpStatus.OK, id, status);
		return ResponseHandler.generateResponse("genre DNE :-(", HttpStatus.BAD_REQUEST, id, status);
		
			
	}
}
