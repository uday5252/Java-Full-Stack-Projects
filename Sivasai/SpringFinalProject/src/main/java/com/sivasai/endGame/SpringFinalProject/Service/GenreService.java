package com.sivasai.endGame.SpringFinalProject.Service;

import com.sivasai.endGame.SpringFinalProject.Entity.Genre;
import com.sivasai.endGame.SpringFinalProject.Repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public Genre saveGenreRecord(Genre genre){
        return genreRepository.save(genre);
    }

    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    public Set<Genre> getByGenreName(Set<String> genres) {
        Set<Genre> genresSet = new HashSet<>();
        for(String genre : genres){
            if(genreRepository.existsByName(genre)){
                Genre g = genreRepository.findByName(genre).get();
                genresSet.add(g);
            }
        }
        return genresSet;
    }

    public Genre updateGenre(Genre genre, int genreId) throws IllegalAccessException, NoSuchFieldException {
        if(ifExists(genreId)) {
            Genre existingGenre = genreRepository.findById(genreId).get();
            for(Field field: genre.getClass().getDeclaredFields()){
                String fieldName = field.getName();
                Field f = existingGenre.getClass().getDeclaredField(fieldName);
                if(fieldName != "id"){
                    field.setAccessible(true);
                    if(field.get(genre) != null){
                        f.setAccessible(true);
                        f.set(existingGenre, field.get(genre));
                    }
                }
            }
            return genreRepository.save(existingGenre);
        }
        else {
            return null;
        }
    }

    public Boolean ifExists(int id){
        return genreRepository.existsById(id);
    }

    public void deleteGenre(int genreId){
        genreRepository.deleteById(genreId);

    }
}
