package com.project.zee.Video.Streaming.Platform.Controller;

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

import com.project.zee.Video.Streaming.Platform.Entity.GenreEntity;
import com.project.zee.Video.Streaming.Platform.Service.GenreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class GenreController {
	
	@Autowired
	GenreService gs;
	
	@ApiResponse
    (
        responseCode = "201"
    )
    @Operation
    (
        summary = "Enter Genre data",

        description = "Add a new genre by entering the name and description of the genre  "
    )
	
	@PostMapping("/api/admin/genres")
	public ResponseEntity<String> AddGenre(@RequestBody GenreEntity genre)
	{
		gs.addgenre(genre);
		gs.sendnewGenreDataToGenreTopic();
		return new ResponseEntity<> ("Genre added succesfully",HttpStatus.CREATED);
	}
	
	 @Operation
	    (
	        summary = "Get all Genres",

	        description = "To get the list of all genres present in the database "
	    )
	
	@GetMapping("/api/genres")
	public ResponseEntity<List<GenreEntity>> GetGenres()
	{
		List<GenreEntity> genres = gs.Getgenres();
		return new ResponseEntity<> (genres,HttpStatus.OK);
	}
	 
	 @Operation
	    (
	        summary = "Update genre ",

	        description = "To update a particular genre by entering the genre id in the path"
	    )
	
	@PutMapping("/api/admin/genres/{genreId}")
	public ResponseEntity<String> UpdateGenre(@RequestBody GenreEntity genre,@PathVariable("genreId") int id)
	{
		gs.UpdateGenre(genre,id);
		gs.sendUpdatedGenreDataToGenreTopic(id);
		return new ResponseEntity<> ("Genre Updated succesfully",HttpStatus.CREATED);
	}
	
	 @Operation
	    (
	        summary = "Delete genre ",

	        description = "To delete a particular genre by entering the genre id in the path"
	    )
	
	@DeleteMapping("/api/admin/genres/{genreId}")
	public ResponseEntity<String> DeleteGenre(@PathVariable("genreId") int id)
	{
		gs.DeleteGenre(id);
		gs.sendDeletedGenreDataToGenreTopic(id);
		return new ResponseEntity<> ("Genre Deleted succesfully",HttpStatus.CREATED);
	}
	

}

