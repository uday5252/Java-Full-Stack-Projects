package com.project.VideoStreamingPlatformUsingSpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.VideoStreamingPlatformUsingSpringBoot.entity.genresEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.repository.GenreRepository;

@Service
public class GenreService {
	@Autowired
	GenreRepository gr;

	public void addgenre(genresEntity ge) {
		gr.save(ge);
	}

	public List<genresEntity> listgenres() {
		return gr.findAll();
	}

	public void updategenre(genresEntity ge, int gid) {
		genresEntity ge1 = gr.findById(gid).get();
		ge1.setDescription(ge.getDescription());
		gr.save(ge1);
	}

	public void deletegenre(int id) {
		gr.deleteById(id);
	}
	
}
