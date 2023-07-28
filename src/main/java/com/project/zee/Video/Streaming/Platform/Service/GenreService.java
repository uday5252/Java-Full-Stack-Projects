package com.project.zee.Video.Streaming.Platform.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.zee.Video.Streaming.Platform.Entity.GenreEntity;
import com.project.zee.Video.Streaming.Platform.Repository.GenreInterface;

@Service
public class GenreService {
	
	@Autowired
	GenreInterface gi;
	
	@Autowired
	KafkaTemplate<Integer,String> kt;
	
	public void addgenre(GenreEntity genre)
	{
		gi.save(genre);
	}
	
	public List<GenreEntity> Getgenres()
	{
		return gi.findAll();
	}
	
	public void UpdateGenre(GenreEntity updatedgenre ,int id)
	{
		GenreEntity genre = gi.findById(id).get();
	
		genre.setName(updatedgenre.getName()!=null ? updatedgenre.getName() : genre.getName());
		
		genre.setDescription(updatedgenre.getDescription()!=null ? updatedgenre.getDescription() : genre.getDescription());
				
		gi.save(genre);		
	}
	
	public void DeleteGenre(int id)
	{
		gi.deleteById(id);
	}
	
	
	
//methods to send to topic
	
	public void sendnewGenreDataToGenreTopic()
	{	
		
		String GenreUpdate = "Admin added a new genre ";
		kt.send("GenreDataTopic",GenreUpdate);
		
	}
	
	public void sendUpdatedGenreDataToGenreTopic(int genreId)
	{	
		
		String GenreUpdate = "Admin updated the genre "+ genreId;
		kt.send("GenreDataTopic",GenreUpdate);
		
	}
	
	public void sendDeletedGenreDataToGenreTopic(int genreId)
	{	
		
		String GenreUpdate = "Admin deleted the genre "+ genreId;
		kt.send("GenreDataTopic",GenreUpdate);
		
	}
	
	
	
	
	

}
