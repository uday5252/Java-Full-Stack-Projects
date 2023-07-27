package com.zee.org.zee5_Clone.Service;


import com.zee.org.zee5_Clone.Entity.GenreTable;
import com.zee.org.zee5_Clone.Repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService
{
    @Autowired
    GenreRepository Gr;

    public List<GenreTable> ViewAllGenres(){
        return Gr.findAll();
    }

    public void createGenre(GenreTable genre){
        Gr.save(genre);
    }
    public GenreTable updateGenre(int a,String name,String discription){
        GenreTable data=Gr.findById(a).get();
        data.setName(name);
        data.setDiscription(discription);
        GenreTable G=Gr.save(data);
        return G;
    }

    public void DeleteGenre(int a){
        Gr.deleteById(a);
    }

}
