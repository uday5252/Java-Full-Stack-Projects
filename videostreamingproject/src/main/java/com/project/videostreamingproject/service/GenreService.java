package com.project.videostreamingproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.videostreamingproject.entity.Genre;
import com.project.videostreamingproject.entity.User;
import com.project.videostreamingproject.repository.GenreRepository;

@Service
public class GenreService {
       
	@Autowired
	GenreRepository Gr;
	
	@Autowired
	KafkaTemplate<String,String> kf;
	public void createdgenre(Genre g)
	{
		Gr.save(g);
	}
	
	public List<Genre> findallgenres()
	{
		return Gr.findAll();
	}
	
	public void update(Genre g,int id)
	{
		Genre o=Gr.findById(id).get();
		o.setGenreName(g.getGenreName()==null?o.getGenreName(): g.getGenreName());
		o.setGenreDescription(g.getGenreDescription()==null?o.getGenreDescription(): g.getGenreDescription());
	//	kf.send("GENERIC-UPDATES",o.getGenreName()+"GENERIC IS UPDATED");
		Gr.save(o);
		
	}
	
	public void delete(int id)
	{
		Genre g=Gr.findById(id).get();
		Gr.delete(g);
		
	}
	
	public Genre find(int id)
	{
		return Gr.findById(id).get();
	}
}
