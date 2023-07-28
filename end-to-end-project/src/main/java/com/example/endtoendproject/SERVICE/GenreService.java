package com.example.endtoendproject.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.endtoendproject.ENTITY.Genre;
import com.example.endtoendproject.REPOSITORY.GenreMangementInterface;

@Service
public class GenreService {
	@Autowired
	GenreMangementInterface gmi;
	
	//add a new genre
	public void insertGenre(Genre genre) {
		gmi.save(genre);
	}
	
	//all genres
	public List<Genre> listGenre(){
		List<Genre> genreList = gmi.findAll();
		return genreList;
	}
	
	
	//update the genres
	public void updateGenre(Genre updateGenre, int gid) {
		Genre genre = gmi.findById(gid).get();
		genre.setGenreId(updateGenre.getGenreId() != 0?updateGenre.getGenreId():genre.getGenreId());
		genre.setName(updateGenre.getName() != null? updateGenre.getName():genre.getName());
		genre.setDescription(updateGenre.getDescription() != null?updateGenre.getDescription():genre.getDescription());
		gmi.save(genre);
	} 
	
	//delete genre
	public void deleteGenre(int gid) {
		gmi.deleteById(gid);
	}
}
