package com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.genreEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.REPOSITORY.genreRepository;

@Service
public class genreService {
	
	
	@Autowired
	genreRepository gr;

	public List<genreEntity> getGenres() {
	
		List<genreEntity> genreData=gr.findAll();
		
		return genreData;
	}

	public void addGenre(genreEntity ge) {
		
		gr.save(ge);
	}

	public genreEntity getGenreById(int id) {
		
		genreEntity genreDetails=gr.findById(id).get();
		
		return genreDetails;
		
	}

	public void deleteGenreById(int id) {
		
		gr.deleteById(id);
	}

}
