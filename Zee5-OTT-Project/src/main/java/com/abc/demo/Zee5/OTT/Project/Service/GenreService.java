package com.abc.demo.Zee5.OTT.Project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.Zee5.OTT.Project.Entity.Genre;
import com.abc.demo.Zee5.OTT.Project.Repository.GenreRepository;

@Service
public class GenreService {
	@Autowired
	GenreRepository gr;
	@Autowired
	KafkaAdmin Admin;
	@Autowired
	KafkaTemplate<String, String> kt;
	public void createGenre(Genre genre)
	{
		gr.save(genre);
	}
	
	public List<Genre> listAllGenres()
	{
		return gr.findAll();
	}
	public Genre findGenre(int id)
	{
		return gr.findById(id).get();
	}
	public void updateGenre(int genreId,Genre genre)
	{
		Genre genreold=gr.findById(genreId).get();
		genreold.setName(genre.getName()!=null?genre.getName():genreold.getName());
		genreold.setDescription(genre.getDescription()!=null?genre.getDescription():genreold.getDescription());
		gr.save(genreold);
		String User="Admin";
		
		kt.send(User," Admin has update the genre "+ genreId);
		
	}
	public void deleteGenre(int genreId)
	{
		gr.deleteById(genreId);
		
	}
}
