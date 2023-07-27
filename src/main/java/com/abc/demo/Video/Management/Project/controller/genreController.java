package com.abc.demo.Video.Management.Project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.Video.Management.Project.entity.genreEntity;
import com.abc.demo.Video.Management.Project.service.genreService;

@RestController
public class genreController {
	@Autowired
	genreService gs;
	@PostMapping("/api/user/genre")
	public ResponseEntity<String> genre(@RequestBody genreEntity ge)
	{
		gs.creategenre(ge);
		return new ResponseEntity<>("created",HttpStatus.CREATED);
	}
	@DeleteMapping("/api/user/genres/{id}")
	public ResponseEntity<String> deleteGenre(@PathVariable ("id") int id)
	{
		gs.deletegenre(id);
		return new ResponseEntity<>("deleted",HttpStatus.ACCEPTED);
	}
	@GetMapping("/api/user/genres")
	public ResponseEntity<List<genreEntity>> allGenre()
	{
		List<genreEntity> list= gs.findAllGenres();
		return new ResponseEntity<List<genreEntity>>(list,HttpStatus.ACCEPTED);
	}
}
