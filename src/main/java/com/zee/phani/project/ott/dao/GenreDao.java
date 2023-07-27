package com.zee.phani.project.ott.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zee.phani.project.ott.dto.GenreRequestDto;
import com.zee.phani.project.ott.dto.GenreResponseDto;
import com.zee.phani.project.ott.entity.GenreEntity;
import com.zee.phani.project.ott.repository.GenreRepository;

@Component
public class GenreDao {
    @Autowired
    GenreRepository repo;

    @Autowired
    ModelMapper mapper;

    public GenreResponseDto createGenre(GenreRequestDto inDto) {
        GenreEntity genre = mapper.map(inDto, GenreEntity.class);
        genre = repo.save(genre);
        GenreResponseDto responseDto = mapper.map(genre, GenreResponseDto.class);
        responseDto.setGenre(genre);
        return responseDto;
    }

    public List<GenreResponseDto> getGenres() {
        List<GenreEntity> genres = repo.findAll();

        List<GenreResponseDto> retVal = genres.stream().map(genre -> {
            GenreResponseDto responseDto = mapper.map(genre, GenreResponseDto.class);
            responseDto.setGenre(genre);
            return responseDto;
        })
                .toList();

        return retVal;
    }

    public GenreResponseDto UpdateGenre(GenreRequestDto inDto, int genreId) throws NoSuchElementException {
        GenreEntity entity = repo.findById(genreId).get();
        entity.setName(inDto.getName() == null ? entity.getName() : inDto.getName());
        entity.setDescription(inDto.getDescription() == null ? entity.getDescription() : inDto.getDescription());
        entity = repo.save(entity);
        GenreResponseDto responseDto = mapper.map(entity, GenreResponseDto.class);
        responseDto.setGenre(entity);
        return responseDto;

    }

    public void deleteGenre(int genId) {
        GenreEntity e = repo.findById(genId).get();
        repo.delete(e);
    }
}
