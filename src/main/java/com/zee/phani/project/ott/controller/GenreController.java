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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Genre Controller", description = "This controller controls all the endpoints associated with the GENRE functions related to the application")

@RestController
public class GenreController {

    @Autowired
    GenreService service;

    @Operation(summary = "Create Genre", description = "This endpoint will take the Genre details and adds it to the Database.", responses = {
            @ApiResponse(responseCode = "201", description = "This response code corresponds to the sucessful insertion of data into the database."),
            @ApiResponse(responseCode = "400", description = "This response code is sent when there is an internal error or there is a problem inserting into the database.")

    })
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

    @Operation(summary = "Get All Genre details", description = "This endpoint will fetch all the Genre details", responses = {
            @ApiResponse(responseCode = "200", description = "This response code corresponds to the sucessful fetching of all the genres from the database. The response can be null if there are no genres in the application database")
    })
    @GetMapping("/api/genres")
    public ResponseEntity<List<GenreResponseDto>> getGenres() {
        List<GenreResponseDto> lists = service.getAllGenres();
        return new ResponseEntity<List<GenreResponseDto>>(lists, HttpStatus.OK);
    }

    @Operation(summary = "Update Genre Details", description = "This endpoint will update the Genre details from the Database.Please provide only the details to be updated along with the genre id in the path.", responses = {
            @ApiResponse(responseCode = "201", description = "This response code corresponds to the sucessful update of data into the database."),
            @ApiResponse(responseCode = "400", description = "This response code is sent when there is an internal error or there is a problem fetching from the database.")

    })
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

    @Operation(summary = "Delete Genre details", description = "This endpoint will take the genre ID and deletes the Genre from the database", responses = {
            @ApiResponse(responseCode = "200", description = "This response code corresponds to the sucessful deletion of data from the application database."),
            @ApiResponse(responseCode = "400", description = "This response code is sent when there is an exception or the genre with the given GENRE ID is not found in the application")

    })
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
