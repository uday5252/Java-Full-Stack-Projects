package com.example.demo.ZeeVideoStreaming.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ZeeVideoStreaming.ENTITY.GenreEntity;
import com.example.demo.ZeeVideoStreaming.ENTITY.UserEntity;
import com.example.demo.ZeeVideoStreaming.REPOSITORY.GenreRepositoryInterface;
import com.example.demo.ZeeVideoStreaming.REPOSITORY.UserRepositoryInterface;

@Service
public class GenreService {

	@Autowired
	GenreRepositoryInterface gri;

	@Autowired
	UserRepositoryInterface uri;

	public GenreEntity createGenreInDB(GenreEntity genre) {
		long id = genre.getUser().getId();
		UserEntity u = uri.findById(id).get();
		genre.setUser(u);
		gri.save(genre);
		return genre;
	}

	public List<GenreEntity> getAllGenresFromDB() {
		return gri.findAll();
	}

	public GenreEntity updateExistingGenreInDB(long id, GenreEntity genreUpdated) {
		GenreEntity genreFromDB = gri.findById(id).get();
		if (genreUpdated.getName() != null)
			genreFromDB.setName(genreUpdated.getName());
		if (genreUpdated.getDescription() != null)
			genreFromDB.setDescription(genreUpdated.getDescription());
		gri.save(genreFromDB);
		return genreFromDB;
	}
	
	public boolean deleteGenreInDB(long id) {
		GenreEntity g = gri.findById(id).get();
		if( g!= null) {
			gri.delete(g);
			return true;
		}
		return false;
	}

}
