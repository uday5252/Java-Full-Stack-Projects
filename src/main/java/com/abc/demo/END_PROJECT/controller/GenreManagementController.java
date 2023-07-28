package com.abc.demo.END_PROJECT.controller;

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

import com.abc.demo.END_PROJECT.Apache_Kafka.KafkaService;
import com.abc.demo.END_PROJECT.entity.GenreEntity;
import com.abc.demo.END_PROJECT.service.GenreService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class GenreManagementController {

	@Autowired
	GenreService gs;
	
	@Autowired
	KafkaService ks;
	
	@Operation
	(
		summary="Add genre",
		description="This API Endpoint is used for creating genre details"
	)
	
	@PostMapping("/api/admin/genre/add")
	public ResponseEntity<String> addGenre(@RequestBody GenreEntity newgenre){
		gs.addGenre(newgenre);
		String message = "Admin added new genre with name "+newgenre.getName()+" and description "+newgenre.getDescription();
		ks.sendDataToGenre_UpdatesTopic(message);
		return new ResponseEntity<>("Genre has been successfully added",HttpStatus.CREATED);
	}
	
	
	@Operation
	(
		summary="Get all genres",
		description="This API Endpoint is used for getting all genre details"
	)
	
	@GetMapping("/api/genres/readAll")
	public ResponseEntity<List<GenreEntity>> readAllGenres(){
		List<GenreEntity> ls = gs.readGenres();
		return new ResponseEntity<List<GenreEntity>>(ls,HttpStatus.OK);
	}
	
	
	
	@Operation
	(
		summary="Update genre",
		description="This API Endpoint is used for updating genre details"
	)
	@PutMapping("/api/admin/genres/{genreid}/update")
	public ResponseEntity<String> updateGenre(@RequestBody GenreEntity genre, @PathVariable("genreid") int gid){
		gs.updateGenre(genre,gid);
		return new ResponseEntity<String>("Genre has been updated succesfully",HttpStatus.ACCEPTED);
	}
	
	@Operation
	(
		summary="Delete genre",
		description="This API Endpoint is used for deleting genre details"
	)
	
	@DeleteMapping("/api/admin/genres/{genreid}")
	public ResponseEntity<String> deleteGenre(@PathVariable("genreid") int gid){
		
		GenreEntity ge = gs.deleteGenre(gid);
		String message = "Admin deleted the genre with name " +ge.getName() +" and with description " +ge.getDescription();
		ks.sendDataToGenre_UpdatesTopic(message);
		
		return new ResponseEntity<>("Genre has been deleted successfully ",HttpStatus.OK);
	}
}
