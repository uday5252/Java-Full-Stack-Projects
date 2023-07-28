package com.example.demo.End.project.SERVICE;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.End.project.ENTITY.GenreEntity;
import com.example.demo.End.project.REPOSITORY.GenreRepositoryInterface;

@Service
public class GenreService {

	@Autowired
	GenreRepositoryInterface gri;
	
	public List<GenreEntity> listOfGenres() {
		List<GenreEntity> ge = gri.findAll();
		return ge;
	}

	public void createGenre(GenreEntity ge) {
		gri.save(ge);
	}

	public void UpdateGenre(GenreEntity ge, int id) {
	   GenreEntity existingData = gri.findById(id).get();
	   existingData.setName(ge.getName()!= null ? ge.getName() : existingData.getName());
	   existingData.setDescription(ge.getDescription() !=null ? ge.getDescription() : existingData.getDescription());
		gri.save(existingData);
	}

	public void deleteGenre(int id) {
		gri.deleteById(id);
		
	}
     
}
