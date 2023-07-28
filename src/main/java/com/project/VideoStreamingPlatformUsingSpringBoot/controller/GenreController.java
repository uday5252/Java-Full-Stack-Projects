package com.project.VideoStreamingPlatformUsingSpringBoot.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.VideoStreamingPlatformUsingSpringBoot.entity.genresEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.kafka.KafkaService;
import com.project.VideoStreamingPlatformUsingSpringBoot.service.GenreService;

@RestController
public class GenreController {
	@Autowired
	GenreService gs;
	@Autowired
	KafkaService ks;
	
	@PostMapping("/api/admin/genres/add")
	public ResponseEntity<String> addGenre(@RequestBody genresEntity ge){
		gs.addgenre(ge);
		ks.sendDataToGenre_UpdatesTopic(ge.getGenreName()+"is added");
		ks.sendDataToGenre_UpdatesTopic(ge.toString());
		return new ResponseEntity<String>("Genre added successfully",HttpStatus.CREATED);
	}
	
	@PutMapping("/api/admin/genres/{id}/update")
	public ResponseEntity<String> updateGenre(@RequestBody genresEntity ge, @PathVariable("id") int gid){
		gs.updategenre(ge,gid);
		ks.sendDataToGenre_UpdatesTopic(ge.getGenreName()+"details are updated");
		ks.sendDataToGenre_UpdatesTopic(ge.toString());
		return new ResponseEntity<String>("Description details updated successfully",HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/admin/genres/{gid}/delete")
	public ResponseEntity<String> deleteGenre(@PathVariable("gid") int id){
		gs.deletegenre(id);
		ks.sendDataToGenre_UpdatesTopic("Genre with id "+id+" is deleted");
		return new ResponseEntity<String>("Genre deleted successfully",HttpStatus.OK);
	}
}
