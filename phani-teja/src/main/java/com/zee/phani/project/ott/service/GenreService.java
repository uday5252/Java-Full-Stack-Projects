package com.zee.phani.project.ott.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zee.phani.project.ott.dao.GenreDao;
import com.zee.phani.project.ott.dto.GenreRequestDto;
import com.zee.phani.project.ott.dto.GenreResponseDto;
import com.zee.phani.project.ott.kafka.KafkaProducer;

@Service
public class GenreService {
    @Autowired
    GenreDao genreDao;

    @Autowired
    KafkaProducer kp;

    String topic = "genre_updates";

    public void addGenre(GenreRequestDto inDto) throws JsonProcessingException {
        // System.out.println(inDto.toString());
        if (Objects.isNull(inDto)) {
            throw new IllegalArgumentException("The genre cannot be null");
        }
        if (Objects.isNull(inDto.getName())) {
            throw new IllegalArgumentException("The Name of the Genre cant be null");
        }
        GenreResponseDto responseDto = genreDao.createGenre(inDto);
        kp.UploadToTopic(topic, responseDto.getGenre());
    }

    public List<GenreResponseDto> getAllGenres() {
        return genreDao.getGenres();
    }

    public void updateGenre(GenreRequestDto inDto, int genreId) throws NoSuchElementException, JsonProcessingException {
        try {
            GenreResponseDto responseDto = genreDao.UpdateGenre(inDto, genreId);
            kp.UploadToTopic(topic, responseDto.getGenre());
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("The Genre with the given GenreID is not present in the application");
        }

    }

    public void deleteGenre(int genId) throws Exception {
        try {
            genreDao.deleteGenre(genId);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No Genre Found with given ID", e.getCause());
        }

    }
}
