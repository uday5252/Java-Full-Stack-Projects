package com.project.ZeeWebApplication.Controller;

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

import com.project.ZeeWebApplication.Entity.GenreEntity;
import com.project.ZeeWebApplication.Service.GenreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(
		name = "Genre API controller",
		description = "This contains all the End points of the Genre Application"
		)
@RestController
public class GenreController {

	@Autowired
	GenreService gs;
	
	@PostMapping("/add/genre")
	@ApiResponse
	(
		responseCode = "201"
	)
	@Operation
	(
		summary = "Insert Genre data",
		description = "Insert the Genre data form user side"
	)
	public ResponseEntity<String> AddtheGenre(@RequestBody GenreEntity ge)
	{
		
		gs.AddtheGenreData(ge);
		
		String gname = ge.getGname();
		
		gs.sendDataToGenreTopic(gname);
		
		return new ResponseEntity<>("Added Genre information sucessfully",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/admin/genres")
	@ApiResponse
	(
		responseCode = "200"
	)
	@Operation
	(
		summary = "Read Genre data",
		description = "read the genre data form user side"
	)
	public ResponseEntity<List<GenreEntity>> AllGenreDetails()
	{
		List<GenreEntity> list = gs.GetAllGenreDetails();
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PutMapping("/api/admin/genre/update/{genreid}")
	@ApiResponse
	(
		responseCode = "200"
	)
	@Operation
	(
		summary = "Update Genre data",
		description = "update the genre data form user side"
	)
	public ResponseEntity<String> UpdateGenreData(@PathVariable("genreid") int gid,@RequestBody GenreEntity ge)
	{
		
		gs.GetTheGenreData(gid,ge);
		
		return new ResponseEntity<>("Updated Genre Data Sucessfully",HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/admin/genre/delete/{genreid}")
	@ApiResponse
	(
		responseCode = "200"
	)
	@Operation
	(
		summary = "Delete Genre data",
		description = "delete the genre data form user side"
	)
	public ResponseEntity<String> DeleteGenre(@PathVariable("genreid") int id)
	{
		gs.DeleteGenre(id);
		
		return new ResponseEntity<>("Genre Deleted sucessfully",HttpStatus.OK);
	}
}
