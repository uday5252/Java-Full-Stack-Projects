package com.abc.Streaming.application.project.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.abc.Streaming.application.project.ENTITIES.GenreEntity;
import com.abc.Streaming.application.project.ENTITIES.VideoEntity;
import com.abc.Streaming.application.project.REPOSITORIES.GenreEntityInterfaceRepositary;
import com.abc.Streaming.application.project.REPOSITORIES.UserEntityInterfaceRepositary;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class GenreService {
	
	@Autowired
	GenreEntityInterfaceRepositary g;

	public void addgenre(GenreEntity ge) 
	{
		
		g.save(ge);
		
	}

	public List<GenreEntity> readgenre() 
	{
		List<GenreEntity>  l = g.findAll();
		return l;
	}

	public void updategenre(GenreEntity ge, int gid)
	{
		
		GenreEntity genre =  g.findById(gid).get();
		genre.setName(ge.getName()!=null?ge.getName():genre.getName());
		genre.setDescription(ge.getDescription()!=null?ge.getDescription():genre.getDescription());
		g.save(genre);
	}

	public void deletegenre(int gid) 
	{
		g.deleteById(gid);		
	}

	public List<GenreEntity> readvideo() {
		// TODO Auto-generated method stub
		        readvideo();		
		    return null;
	}


	
	
	
	

}
