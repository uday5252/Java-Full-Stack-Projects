package com.xyz.demo.EndProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.xyz.demo.EndProject.Entity.GenreEntity;
import com.xyz.demo.EndProject.Interface.GenreRepo;


@Service
public class GenreService {
	   
	@Autowired
	GenreRepo gr;
	
	 @Autowired
	 KafkaTemplate<Integer, String> kt;

	
		public List<GenreEntity> getAllGenres() {
	        return gr.findAll();
	    }

	    public GenreEntity addGenre(GenreEntity genre) {
	        return gr.save(genre);
	    }

	    public GenreEntity updateGenre(int genreId, GenreEntity genre) {
	        GenreEntity existingGenre = gr.findById(genreId).orElse(null);
	        if (existingGenre != null) {
	            existingGenre.setGenreName(genre.getGenreName());
	            existingGenre.setGenreDescription(genre.getGenreDescription());
	            kt.send("genre_updates",existingGenre.toString());
	            return gr.save(existingGenre);
	        } else {
	            return null;
	        }
	    }

	    public boolean deleteGenre(int genreId) {
	        if (gr.existsById(genreId)) {
	            gr.deleteById(genreId);
	            return true;
	        } else {
	            return false;
	        }
	    }
	}


