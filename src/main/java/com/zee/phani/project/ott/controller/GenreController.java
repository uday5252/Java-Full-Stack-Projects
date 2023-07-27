package com.zee.phani.project.ott.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zee.phani.project.ott.dto.GenreRequestDto;
import com.zee.phani.project.ott.dto.GenreResponseDto;
import com.zee.phani.project.ott.service.GenreService;

@RestController
public class GenreController {

    @Autowired
    GenreService service;

    @PostMapping("/api/admin/genres")
    public ResponseEntity<String> postGenre(@RequestBody GenreRequestDto genreDto) {
        try {
            service.addGenre(genreDto);
            return new ResponseEntity<String>("Added Genre", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>("The Genre Name should be unique", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/genres")
    public ResponseEntity<List<GenreResponseDto>> getGenres() {
        List<GenreResponseDto> lists = service.getAllGenres();
        return new ResponseEntity<List<GenreResponseDto>>(lists, HttpStatus.OK);
    }

    @PutMapping("/api/admin/genres/{genreId}")
    public ResponseEntity<String> updateGenre(@RequestBody GenreRequestDto inDto,
            @PathVariable("genreId") int genreId) {
        try {
            service.updateGenre(inDto, genreId);
            return new ResponseEntity<String>("Sucessfully Updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/admin/genres/{genreId}")
    public ResponseEntity<String> deleteGenre(@PathVariable("genreId") int genreId) {
        try {
            service.deleteGenre(genreId);
            return new ResponseEntity<String>("Genre Delted Sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
