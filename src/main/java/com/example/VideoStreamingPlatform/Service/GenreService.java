package com.example.VideoStreamingPlatform.Service;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.VideoStreamingPlatform.Entity.GenreEntity;
import com.example.VideoStreamingPlatform.Repository.GenreRepository;

@Service
public class GenreService {
	
	@Autowired
	GenreRepository gr;
	
	public void addGenre(GenreEntity genre) {
		gr.save(genre);
	}
	
	public List<GenreEntity> getAllGenres() {
		List<GenreEntity> list = new ArrayList<GenreEntity>();
		gr.findAll().forEach(genre->list.add(genre));
		return list;
	}
	
	public GenreEntity getGenreById(int id) {
		return gr.findById(id).get();
	}
	
	public void updateGenre(GenreEntity genre, int id) throws ResourceNotFoundException{
		GenreEntity existingGenre = gr.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre not found for this id"));
		
		existingGenre.setName(genre.getName());
		existingGenre.setDescription(genre.getDescription());
		gr.save(existingGenre);
		
//		return "Genre Updated Successfully";
		
	}
	
	public void deleteGenre(int id) {
		gr.deleteById(id);
	}
	

}
