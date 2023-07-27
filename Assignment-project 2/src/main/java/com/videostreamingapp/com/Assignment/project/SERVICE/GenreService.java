package com.videostreamingapp.com.Assignment.project.SERVICE;

import com.videostreamingapp.com.Assignment.project.ENTITY.GenreEntity;

import com.videostreamingapp.com.Assignment.project.REPOSITORY.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {
    @Autowired
    GenreRepository gr;

    public void genreCreate(GenreEntity ge) {
        gr.save(ge);
    }

    public List<String> readAllGenre() {
        List<String> l = new ArrayList<>();
        List<GenreEntity> l1 = gr.findAll();

        for(GenreEntity g : l1){
            l.add(g.getGenre_name());
        }
        return  l;

    }

    public GenreEntity updateGenre(GenreEntity genre, int genre_id){
        GenreEntity ge = gr.findById(genre_id).get();
        ge.setGenre_name(genre.getGenre_name()!=null ? genre.getGenre_name():ge.getGenre_name());
        ge.setDescription(genre.getDescription()!= null ? genre.getDescription():ge.getDescription());

        gr.save(ge);

        return ge;
    }


    public void deleteGenre(int genre_id){
        GenreEntity gen = gr.findById(genre_id).get();
        gr.delete(gen);

    }
}
