package com.example.endtoendproject.CONTROLLER;

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
import com.example.endtoendproject.ENTITY.Genre;
import com.example.endtoendproject.PRODUCER.KafkaProducer;
import com.example.endtoendproject.SERVICE.GenreService;

//Task 2: Genre Management
//Endpoints
//1. `GET /api/genres`: Get a list of all available genres.
//2. `POST /api/admin/genres`: Add a new genre (Admin only).
//3. `PUT /api/admin/genres/{genreId}`: Update an existing genre (Admin only). 
//4. `DELETE /api/admin/genres/{genreId}`: Delete a genre (Admin only).
@RestController
public class GenreController {
	@Autowired
	GenreService gs;
	@Autowired
	KafkaProducer kp;
	@PostMapping("/api/admin/genres")
	public ResponseEntity<String> insertGenre(@RequestBody Genre genre){
		gs.insertGenre(genre);
		System.out.println(genre);
		kp.sendData4("genre has been uploaded");
		return new ResponseEntity<String>("Genre successfully added",HttpStatus.CREATED);
	}

	@GetMapping("/api/genres")
	public ResponseEntity<List<Genre>> showGenre(){
		List<Genre> genres = gs.listGenre();
		return new ResponseEntity<>(genres,HttpStatus.OK);
	}
	
	@PutMapping("/api/admin/genres/{genreId}")
	public ResponseEntity<String> updateGenData(@RequestBody Genre updateGen,@PathVariable("genreId") int gid){
		gs.updateGenre(updateGen, gid);
		return new ResponseEntity<>("Genre updated successfully",HttpStatus.OK);
	}
	
	@DeleteMapping("/api/admin/genres/{genreId}")
	public ResponseEntity<String> deleteGen(@PathVariable("genreId") int gid){
		gs.deleteGenre(gid);
		return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
	}
	
}
