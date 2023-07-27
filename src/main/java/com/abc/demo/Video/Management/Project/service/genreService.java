package com.abc.demo.Video.Management.Project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.demo.Video.Management.Project.entity.genreEntity;
import com.abc.demo.Video.Management.Project.repository.genreRepositoryInterface;

@Service
public class genreService {
	@Autowired
	genreRepositoryInterface gri;
	public void creategenre(genreEntity genre)
	{
		gri.save(genre);
	}
	public void deletegenre(int id)
	{
		gri.delete(gri.findById(id).get());
	}
	public List<genreEntity> findAllGenres()
	{
		List<genreEntity> list=gri.findAll();
		return list;
	}
}
