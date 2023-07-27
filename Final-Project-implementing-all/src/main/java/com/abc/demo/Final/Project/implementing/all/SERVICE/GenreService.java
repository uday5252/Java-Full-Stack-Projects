package com.abc.demo.Final.Project.implementing.all.SERVICE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.Final.Project.implementing.all.Entity.GenreEntity;
import com.abc.demo.Final.Project.implementing.all.REPOSITORY.GenreRepository;

@Service
public class GenreService {

	@Autowired
	GenreRepository gr;
	
	@Autowired
	KafkaTemplate<Integer,String> kt;
	
	public void Genreadd(GenreEntity ge) {
		gr.save(ge);
	}
	
	public List<String> Genreget(){
		List<String> l = new ArrayList<String>();
		List<GenreEntity> l1 = gr.findAll();
		for(GenreEntity g : l1) {
			l.add(g.getName());
		}
		return l;
	}
	
	
	public void Genreupdate(GenreEntity ge,int gid) {
		GenreEntity g = gr.findById(gid).get();
		g.setName(ge.getName() != null ? ge.getName() : g.getName());
		g.setDescription(ge.getDescription() != null ? ge.getDescription() : g.getDescription());
		
		gr.save(g);
		kt.send("GenreUpdates","Genre Is Updated");
	}
	
	public void Genredelete(int gid) {
		GenreEntity g = gr.findById(gid).get();
		gr.delete(g);
				
	}
}
