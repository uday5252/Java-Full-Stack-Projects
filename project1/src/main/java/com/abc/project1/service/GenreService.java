package com.abc.project1.service;

import com.abc.project1.ExceptionHandling.CustomException.InvalidIdException;
import com.abc.project1.ExceptionHandling.CustomException.ResourceNotFoundException;
import com.abc.project1.entity.Genre;
import com.abc.project1.entity.Video;
import com.abc.project1.repository.GenreRepo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GenreService {

    @Autowired
    GenreRepo gr;

    public Genre addThisGenre(Genre genre) {
        return gr.save(genre);
    }

    public List<Genre> findAllGenres() {
        return gr.findAll();
    }

    public Genre updateThisGenre(int genreId, Genre detailsToUpdate) throws InvalidIdException {
        if(genreId <= 0){
            throw new InvalidIdException(genreId);
        }
        Genre genreToUpdate = gr.findByGid(genreId);
        if(detailsToUpdate.getGenreName() != null && genreToUpdate.getGenreName().length() != 0){
            genreToUpdate.setGenreName(detailsToUpdate.getGenreName());
        }
        if(detailsToUpdate.getGenreDescription() != null && genreToUpdate.getGenreDescription().length() != 0){
            genreToUpdate.setGenreDescription(detailsToUpdate.getGenreDescription());
        }
        gr.save(genreToUpdate); // not necessary, autosave will happen because genreToUpdate didn't detached yet

        return genreToUpdate;
    }

    public void deleteThisGenre(int genreId) throws InvalidIdException {
        if(genreId <= 0){
            throw new InvalidIdException(genreId);
        }
        Genre genreToDelete = gr.findByGid(genreId);
        gr.delete(genreToDelete);
    }

    public Genre getByGenreId(int genreId) throws InvalidIdException {
        if(genreId <= 0){
            throw new InvalidIdException(genreId);
        }
        return gr.findByGid(genreId);
    }

    public Set<Video> getVideosByGenreId(int genreId) throws InvalidIdException {
        if(genreId <= 0){
            throw new InvalidIdException(genreId);
        }
        Genre genre = gr.findByGid(genreId);
        return genre.getVideos();
    }

    public Genre getByGenreName(String genreName) throws Exception {
        Genre genre = gr.findByGenreName(genreName);
        if(genre == null){
            throw new Exception("genre not found with genreName "+genreName);
        }
        return genre;
    }
}
