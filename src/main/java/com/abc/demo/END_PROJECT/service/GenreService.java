package com.abc.demo.END_PROJECT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.demo.END_PROJECT.entity.GenreEntity;
import com.abc.demo.END_PROJECT.repository.GenreRepository;

@Service
public class GenreService {
	
	@Autowired
	GenreRepository gr;

	public void addGenre(GenreEntity newgenre) {

		gr.save(newgenre);
	}

	public List<GenreEntity> readGenres() {
		
		List<GenreEntity> ls = gr.findAll();
		return ls;
	}

	public void updateGenre(GenreEntity genre, int gid) {
		GenreEntity ge = gr.findById(gid).get();
//		ge=genre;
		ge.setName(genre.getName()!=null ? genre.getName():ge.getName());
		ge.setDescription(genre.getDescription()!=null ? genre.getDescription():ge.getDescription());
		
		gr.save(ge);
	}

	public GenreEntity deleteGenre(int gid) {

		GenreEntity ge = gr.findById(gid).get();
		gr.delete(ge);
		return ge;
		
	}

}
