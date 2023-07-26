package com.example.VideoStreamingPlatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.VideoStreamingPlatform.entity.Genre;
import com.example.VideoStreamingPlatform.repository.GenreRepository;

@Service
public class GenreService {

  @Autowired
  GenreRepository gr;

  public Genre createGenre(Genre newGenre) {
    gr.save(newGenre);
    return gr.findById(newGenre.getGenre_id()).get();
  }

  public List<Genre> getAllGenres() {
    return gr.findAll();
  }

  public Genre updateGenre(int genre_id, Genre genre) {
    Genre orgGenre = gr.findById(genre_id).get();
    orgGenre.setName(genre.getName() == null ? orgGenre.getName() : genre.getName());
    orgGenre.setDescription(genre.getDescription() == null ? orgGenre.getDescription() : genre.getDescription());
    gr.save(orgGenre);
    return orgGenre;
  }

  public void deleteGenre(int genre_id) {
    gr.delete(gr.findById(genre_id).get());
  }
}
