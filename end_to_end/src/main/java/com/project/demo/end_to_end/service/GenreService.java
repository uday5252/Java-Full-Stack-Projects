package com.project.demo.end_to_end.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.end_to_end.entities.Genre;
import com.project.demo.end_to_end.repository.GenreRepositoryInterface;

@Service
public class GenreService {
    @Autowired
    GenreRepositoryInterface gri;
    public List<Genre> getAllGenres(){
        return gri.findAll();
    }
    public Genre getById(int id) {
        return gri.findById(id).get();
    }
    public void save(Genre g) {
        gri.save(g);
    }
    public void deleteGenre(int id) {
        gri.deleteById(id);
    }
    public void addGenre(Genre g) {
        gri.save(g);
    }
    public Genre getByName(String genre) {
        return gri.findByName(genre);
    }
}
