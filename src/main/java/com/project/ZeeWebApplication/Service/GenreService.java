package com.project.ZeeWebApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.ZeeWebApplication.Entity.GenreEntity;
import com.project.ZeeWebApplication.Repository.GenreRepositoryInterface;

@Service
public class GenreService {

	@Autowired
	GenreRepositoryInterface gri;
	
	@Autowired
	KafkaTemplate<Integer, String> kt;

	public void AddtheGenreData(GenreEntity ge) {
		
		gri.save(ge);
		
	}

	public List<GenreEntity> GetAllGenreDetails() {
		
		List<GenreEntity> list = gri.findAll();
		
		return list;
		
	}

	public void GetTheGenreData(int gid, GenreEntity newdata) {
	
		GenreEntity olddata = gri.findById(gid).get();
		
		olddata.setGname(newdata.getGname() != null ? newdata.getGname() : olddata.getGname());

		olddata.setDescription(newdata.getDescription() != null ? newdata.getDescription() : olddata.getDescription());
		
		gri.save(olddata);
	}

	public void DeleteGenre(int id) {
		
		gri.deleteById(id);
		
	}

	public void sendDataToGenreTopic(String gname) {
	   
		String message = "Admin added a new genre name that is :- " + gname;
		
		kt.send("genre_updates",message);
		
	}


}
