package com.sam.demo.streaming.app.zee5.SERVICE;

import com.sam.demo.streaming.app.zee5.ENTITY.GenreEntity;
import com.sam.demo.streaming.app.zee5.REPOSITORY.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepository gr;

    public  void  addGenre(GenreEntity collectGenreData){
        gr.save(collectGenreData);
    }



    public List<GenreEntity> getGenre(){
       return gr.findAll();
    }

    public void updateGenreById(int id, GenreEntity ge)
    {

        GenreEntity  gedata =gr.findById(id).get();
        gedata.setName(ge.getName()!= null? ge.getName():gedata.getName());
        gedata.setDescription(ge.getDescription()!= null?ge.getDescription(): gedata.getDescription());
        gr.save(gedata);
    }
    public void deleteGenreById(int id)
    {
        GenreEntity ge=gr.findById(id).get();
        gr.delete(ge);


    }











}
