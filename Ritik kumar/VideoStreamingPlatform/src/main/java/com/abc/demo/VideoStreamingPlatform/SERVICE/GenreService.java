package com.abc.demo.VideoStreamingPlatform.SERVICE;

import com.abc.demo.VideoStreamingPlatform.ENTITY.GenreEntity;
import com.abc.demo.VideoStreamingPlatform.REPOSITORY.GenreRepositoryInterface;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepositoryInterface gri;

    @Autowired
    KafkaTemplate<Integer,String> kt;

    public void createGenre(GenreEntity genre)
    {
        gri.save(genre);
    }

    public List<GenreEntity> showAllGenre()
    {
        return gri.findAll();
    }

    public void updateGenre(GenreEntity updatedGenre,int genreId)
    {
        GenreEntity genreData=gri.findById(genreId).get();
        genreData.setGenreName(updatedGenre.getGenreName() != null ? updatedGenre.getGenreName() : genreData.getGenreName());
        genreData.setGenreDescription(updatedGenre.getGenreDescription() != null ? updatedGenre.getGenreDescription() : genreData.getGenreDescription());
        gri.save(genreData);

        String genreMessage="genre is updated";
        kt.send("genreTopic",genreMessage);
    }

    public void deleteGenre(int genreId)
    {
        gri.delete(gri.findById(genreId).get());
    }
}
